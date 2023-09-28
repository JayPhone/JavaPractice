package com.jayphone.practice.common;

import com.jayphone.practice.entity.Student;
import com.jayphone.practice.entity.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test2 {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-depends.xml");
        Student student = (Student) applicationContext.getBean("student");
        User user = (User) applicationContext.getBean("user");
    }
}
