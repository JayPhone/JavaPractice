package com.jayphone.practice.zmq.pub_sub;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * @author JayPhone
 * @description 没有订阅者也会继续发送数据
 * @date 2023/9/8
 */
public class ZMQPublisher {
    public static void main(String[] args) throws InterruptedException {
        ZContext context = new ZContext();
        ZMQ.Socket publisher = context.createSocket(SocketType.PUB);
        publisher.bind("tcp://*:5556");
        for (int i = 0; i < 100000; i++) {
            String topic1 = "topic1";
            String content1 = "publisher " + i;
            //设置主题
            publisher.sendMore(topic1);
            publisher.send(content1);
            System.out.println("发布主题: " + topic1 + " 内容: " + content1);

            String topic2 = "topic2";
            String content2 = "publisher" + i;
            //设置主题
            publisher.sendMore(topic2);
            publisher.send(content2);
            System.out.println("发布主题: " + topic2 + " 内容: " + content2);
            Thread.sleep(1000);
        }
    }
}
