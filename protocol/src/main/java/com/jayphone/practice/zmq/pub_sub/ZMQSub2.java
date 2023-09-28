package com.jayphone.practice.zmq.pub_sub;

import org.zeromq.SocketType;
import org.zeromq.ZMQ;

/**
 * @author JayPhone
 * @description 从订阅时开始接收消息，会丢失没有订阅前的消息
 * @date 2023/9/8
 */
public class ZMQSub2 {
    public static void main(String[] args) {
        ZMQ.Context context = ZMQ.context(1);
        ZMQ.Socket subscribe = context.socket(SocketType.SUB);
        subscribe.connect("tcp://127.0.0.1:5556");
        //不订阅收不到消息(ZMQ.SUBSCRIPTION_ALL可订阅所有消息)
        subscribe.subscribe("topic2");
        while (true) {
            System.out.println("接收主题：" + subscribe.recvStr() + " 内容：" + subscribe.recvStr());
        }
    }
}
