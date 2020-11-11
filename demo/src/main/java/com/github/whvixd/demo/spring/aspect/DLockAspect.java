package com.github.whvixd.demo.spring.aspect;

import cn.hutool.crypto.SecureUtil;
import com.github.whvixd.demo.spring.model.DKeyAlias;
import com.github.whvixd.demo.spring.model.DLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.stream.IntStream;

/**
 * Created by wangzhixiang on 2020/11/10.
 */
@Aspect
@Component
@Slf4j
public class DLockAspect {

    // TODO: 2020/11/10 从bean factory中获取
    class CacheClient{

        public boolean setnx(String redisKey, String s, int timeToLiveSeconds) {

            return true;
        }

        public void del(String redisKey) {

        }
    }

    // 用法
    @DLock(idempotentKeys = {"#name","#id"})
    public void test(String name,int id){}

    @Autowired
    private CacheClient cacheClient;


    @Pointcut("@annotation(com.github.whvixd.demo.spring.model.DLock)")
    public void dLockAspect() {
    }

    @Around("dLockAspect()")
    public Object lockAround(ProceedingJoinPoint proceeding) {
        // 获取redis锁
        boolean locked = this.getDLock(proceeding);
        if (locked) {
            try {
                return proceeding.proceed();
            } catch (Throwable throwable) {
                log.error("DLockAspect->lockAround error,message:{} ", throwable.getMessage(), throwable);
                throw new RuntimeException(throwable);
            } finally {
                // 删除锁
                this.delLock(proceeding);
            }
        } else {
            log.info("DLockAspect->lockAround don't get lock,threadName:{}", Thread.currentThread().getName());
            return null;
        }
    }

    /**
     * 获取锁
     */
    private boolean getDLock(ProceedingJoinPoint proceeding) {
        DLockModel dLockModel = this.getDLockModel(proceeding);
        if (dLockModel == null) {
            return false;
        }
        String lockPrefix = dLockModel.getLockPrefix();
        String lockKey = dLockModel.getLockKey();
        String idempotentId = dLockModel.getIdempotentId();
        int timeToLiveSeconds = dLockModel.getTimeToLiveSeconds();
        if (StringUtils.isEmpty(lockPrefix) || StringUtils.isEmpty(lockKey)) {
            log.warn("DLockAspect->getDLock check false,lockPrefix:{},lockKey:{}", lockPrefix, lockKey);
            return false;
        }
        String redisKey = getRedisKey(lockPrefix,lockKey,idempotentId);
        return cacheClient.setnx(redisKey,"1", timeToLiveSeconds);
    }

    /**
     * 删除锁
     */
    private void delLock(ProceedingJoinPoint proceeding) {
        DLockModel dLockModel = this.getDLockModel(proceeding);
        if (dLockModel == null) {
            return;
        }
        String lockPrefix = dLockModel.getLockPrefix();
        String lockKey = dLockModel.getLockKey();
        String idempotentId = dLockModel.getIdempotentId();
        cacheClient.del(getRedisKey(lockPrefix, lockKey,idempotentId));
    }


    /**
     * 获取锁参数
     */
    private DLockModel getDLockModel(ProceedingJoinPoint proceeding) {
        MethodSignature sign = (MethodSignature) proceeding.getSignature();
        Method method = sign.getMethod();
        DLock dLock = method.getAnnotation(DLock.class);
        String lockPrefix = dLock.lockPrefix();
        String lockKey = dLock.lockKey();
        String[] idempotentKeys = dLock.idempotentKeys();
        Object[] args = proceeding.getArgs();
        Parameter[] parameters = method.getParameters();

        return DLockModel.builder()
                .lockPrefix(StringUtils.isBlank(lockPrefix)?method.getDeclaringClass().getSimpleName():lockPrefix)
                .lockKey(StringUtils.isBlank(lockKey)?method.getName():lockKey)
                .timeToLiveSeconds(Long.valueOf(dLock.timeUnit().toSeconds(dLock.expireTime())).intValue())
                .idempotentId(getIdempotentId(idempotentKeys,args,parameters))
                .build();
    }

    private EvaluationContext getEvaluationContext(Object[] args, Parameter[] parameters){
        EvaluationContext context = new StandardEvaluationContext();
        IntStream.range(0,parameters.length).forEach(i->{
            Parameter parameter = parameters[i];
            context.setVariable(getVariableName(parameter), args[i]==null?StringUtils.EMPTY:args[i]);
        });
        return context;
    }

    private String getVariableName(Parameter parameter){
        if(parameter==null){
            return StringUtils.EMPTY;
        }
        DKeyAlias annotation = parameter.getAnnotation(DKeyAlias.class);
        return annotation==null?parameter.getName():
                StringUtils.defaultString(annotation.value(),parameter.getName());
    }

    private String getIdempotentId(String[] idempotentKeys, Object[] args, Parameter[] parameters) {
        if(org.springframework.util.ObjectUtils.isEmpty(idempotentKeys)){
            return StringUtils.EMPTY;
        }
        EvaluationContext evaluationContext = getEvaluationContext(args, parameters);
        StringBuilder builder = new StringBuilder();
        for (String idempotentKey:idempotentKeys) {
            String elValue = String.valueOf(getElValue(evaluationContext, idempotentKey));
            if(StringUtils.isNotBlank(elValue)){
                builder.append(elValue).append(":");
            }
        }
        String result = builder.toString();
        return StringUtils.isNotBlank(result)? SecureUtil.md5(result):result;
    }

    private Object getElValue(EvaluationContext context,String idempotentKey){
        if(StringUtils.isBlank(idempotentKey)||context==null){
            return StringUtils.EMPTY;
        }
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(idempotentKey);
        try {
            return expression.getValue(context);
        }catch (EvaluationException e){
            return StringUtils.EMPTY;
        }
    }

    private String getRedisKey(String lockPrefix, String lockKey, String idempotentId) {
        return lockPrefix + "-" + lockKey + (StringUtils.isNotBlank(idempotentId) ? "-" + idempotentId : StringUtils.EMPTY);
    }
}
