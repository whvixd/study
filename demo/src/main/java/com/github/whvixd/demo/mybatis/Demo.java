package com.github.whvixd.demo.mybatis;

import com.github.whvixd.demo.mybatis.mapper.StudentMapper;
import com.github.whvixd.demo.mybatis.model.Student;
import com.github.whvixd.util.FastjsonUtil;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by wangzhx on 2020/3/7.
 */
public class Demo {
    public static void main(String[] args) throws IOException {
//------- 传统方式
        //读取配置文件
        InputStream inputStream = Resources.getResourceAsStream("mybatis/config.xml");
        //将配置文件转位Configuration对象，构建者模式封装成 DefaultSqlSessionFactory 对象；若是mapper方式，则在getMappers中进行注解的扫描加载属性
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        // 生成 DefaultSqlSession ，创建了executor执行器，设置事务是不自动提交
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 根据namespace.id 从configuration获取MappedStatement，委派给executor执行，executor委派给PreparedStatementHandler执行，
        // Java类型与JDBC类型的转换委派给TypeHandler处理，返回的结果集ResultSetHandler处理
//        List<Object> objects = sqlSession.selectList("namespace.id");


//-------mapper方式
        //利用jdk动态代理了UserMapper的实现类，还是调用selSession中的方法
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        List<Student> all = mapper.findAll();
        System.out.println(FastjsonUtil.toJson(all));
    }
}
