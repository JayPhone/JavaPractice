package com.jayphone.practice.test;

import com.jayphone.practice.entity.Account;
import com.jayphone.practice.entity.Student;
import com.jayphone.practice.repository.AccountRepository;
import com.jayphone.practice.repository.StudentRepository;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Test3 {
    public static void main(String[] args) {
        try {
            InputStream inputStream = Resources.getResourceAsStream("config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            StudentRepository studentRepository = sqlSession.getMapper(StudentRepository.class);
            Student student = studentRepository.findById(1L);
            System.out.println(student);
            sqlSession.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
