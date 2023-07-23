package com.jayphone.practice.test;

import com.jayphone.practice.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test3 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-p.xml");
        Student student = (Student) applicationContext.getBean("student");
        System.out.println(student);
    }
}