package com.jayphone.practice.test;

import com.jayphone.practice.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
//        Student student = (Student) applicationContext.getBean("student");
//        System.out.println(student);
        Student student2 = (Student) applicationContext.getBean("student2");
        System.out.println(student2);
    }
}
