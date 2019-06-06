package com.github.whvixd.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by wangzhx on 2019/1/12.
 */
@Slf4j
public class JobTest implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        try {
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Object jobDetailValue = jobExecutionContext.getJobDetail().getJobDataMap().get("jobDetailKey");
        Object triggerValue = jobExecutionContext.getTrigger().getJobDataMap().get("triggerKey");
        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();

        //获取jobDetailValue和triggerValue的合集，若key一样获取trigger key
        Object contextValue = mergedJobDataMap.get("jobDetailKey");
        log.info("NOW:{},jobDetailValue:{},triggerValue:{},contextValue:{}", simpleDateFormat.format(new Date()), jobDetailValue, triggerValue, contextValue);
    }
}
