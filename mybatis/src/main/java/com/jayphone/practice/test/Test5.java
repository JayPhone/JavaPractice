package com.jayphone.practice.test;

import com.jayphone.practice.entity.Classes;
import com.jayphone.practice.entity.Customer;
import com.jayphone.practice.repository.ClassesRepository;
import com.jayphone.practice.repository.CustomerRepository;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Test5 {
    public static void main(String[] args) {
        try {
            InputStream inputStream = Resources.getResourceAsStream("config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            CustomerRepository customerRepository = sqlSession.getMapper(CustomerRepository.class);
            Customer customer = customerRepository.findById(1L);
            System.out.println(customer);
            sqlSession.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
