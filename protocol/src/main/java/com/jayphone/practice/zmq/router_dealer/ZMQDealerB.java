package com.jayphone.practice.zmq.router_dealer;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * @author JayPhone
 * @description
 * @date 2023/9/11
 */
public class ZMQDealerB {
    public static void main(String[] args) throws InterruptedException {
        ZContext context = new ZContext();
        ZMQ.Socket dealerSocket = context.createSocket(SocketType.DEALER);
        dealerSocket.setIdentity("ZMQDealerB".getBytes(ZMQ.CHARSET));
        //连接到Router
        dealerSocket.connect("tcp://127.0.0.1:5559");

        for (int i = 0; i < 100000; i++) {
            //发送消息给Router
            dealerSocket.send("来自ZMQDealerB发送的消息");

            //接收Router的响应
            String response = dealerSocket.recvStr();
            System.out.println("接收Router响应消息：" + response);
            Thread.sleep(1000);
        }
    }
}
