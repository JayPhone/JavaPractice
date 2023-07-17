package com.jayphone.practice.test;

import com.jayphone.practice.entity.Account;
import com.jayphone.practice.entity.Goods;
import com.jayphone.practice.repository.AccountRepository;
import com.jayphone.practice.repository.GoodsRepository;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Test8 {
    public static void main(String[] args) {
        try {
            InputStream inputStream = Resources.getResourceAsStream("config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            AccountRepository accountRepository = sqlSession.getMapper(AccountRepository.class);
//            Account param = new Account();
//            param.setPassword("123123");
//            param.setUsername("张三");
//            List<Account> account = accountRepository.findAllByAccount(param);
            List<Long> idList = new ArrayList<>();
            idList.add(1L);
            idList.add(3L);
            List<Account> account = accountRepository.findById(idList);
            System.out.println(account);
            sqlSession.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
