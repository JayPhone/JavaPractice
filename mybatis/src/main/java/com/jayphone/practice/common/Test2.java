package com.jayphone.practice.common;

import com.jayphone.practice.entity.Account;
import com.jayphone.practice.repository.AccountRepository;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Test2 {
    public static void main(String[] args) {
        try {
            InputStream inputStream = Resources.getResourceAsStream("config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
//            Account account = new Account();
//            account.setUsername("李四");
//            account.setPassword("123456");
//            account.setAge(15);
//            accountRepository.save(account);
//            sqlSession.commit();
//
//            List<Account> accountList = accountRepository.findAll();
//            accountList.forEach(System.out::println);

            Account account = accountRepository.findByUsernameAndAge("张三", 22);
            System.out.println(account);
            sqlSession.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
