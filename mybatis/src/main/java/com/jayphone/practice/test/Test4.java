package com.jayphone.practice.test;

import com.jayphone.practice.entity.Classes;
import com.jayphone.practice.entity.Student;
import com.jayphone.practice.repository.ClassesRepository;
import com.jayphone.practice.repository.StudentRepository;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Test4 {
    public static void main(String[] args) {
        try {
            InputStream inputStream = Resources.getResourceAsStream("config.xml");
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
            SqlSession sqlSession = sqlSessionFactory.openSession();

            ClassesRepository classesRepository = sqlSession.getMapper(ClassesRepository.class);
            Classes classes = classesRepository.findById(1L);
            System.out.println(classes);
            sqlSession.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
