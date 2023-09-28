package com.jayphone.practice.zmq.pub_sub;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * @author JayPhone
 * @description 从订阅时开始接收消息，会丢失没有订阅前的消息
 * @date 2023/9/8
 */
public class ZMQSub1 {
    public static void main(String[] args) {
        ZContext context = new ZContext();
        ZMQ.Socket subscribe = context.createSocket(SocketType.SUB);
        subscribe.connect("tcp://127.0.0.1:5556");
        //不订阅收不到消息(ZMQ.SUBSCRIPTION_ALL可订阅所有消息)
        subscribe.subscribe("topic1");
        while (true) {
            System.out.println("接收主题：" + subscribe.recvStr() + " 内容：" + subscribe.recvStr());
        }
    }
}
