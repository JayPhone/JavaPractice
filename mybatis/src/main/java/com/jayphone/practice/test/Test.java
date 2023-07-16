package com.jayphone.practice.test;

import com.jayphone.practice.entity.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Test {
    public static void main(String[] args) {
        try {
            InputStream inputStream = Resources.getResourceAsStream("config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();
            String statement = "com.jayphone.practice.mapper.AccountMapper.save";

            Account account = new Account();
            account.setUsername("张三");
            account.setPassword("123123");
            account.setAge(22);
            sqlSession.insert(statement,account);
            sqlSession.commit();
            sqlSession.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
