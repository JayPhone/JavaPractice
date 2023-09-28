package com.jayphone.practice.common;

import com.jayphone.practice.entity.Student;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring.xml");
        Student student = (Student) applicationContext.getBean("student");

        Student student2 = (Student) applicationContext.getBean("student");
        System.out.println(student == student2);
    }
}
