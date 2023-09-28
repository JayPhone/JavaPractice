package com.jayphone.practice.common;

import com.jayphone.practice.aop.Cal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test6 {
    public static void main(String[] args) {
//        Cal cal = new CalImpl();
//        cal.add(1, 2);
//        cal.sub(3, 2);
//        cal.mul(2, 3);
//        cal.div(6, 2);

//        Cal cal = new CalImpl();
//        MyInvocationHandler myInvocationHandler = new MyInvocationHandler();
//        Cal cal1 = (Cal) myInvocationHandler.bind(cal);
//        cal1.add(1, 2);
//        cal1.sub(3, 2);
//        cal1.mul(2, 3);
//        cal1.div(6, 2);

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-aop.xml");
        Cal cal = (Cal) applicationContext.getBean("calImpl");
        cal.add(1, 2);
        cal.sub(3, 2);
        cal.mul(2, 3);
        cal.div(6, 0);
    }
}
