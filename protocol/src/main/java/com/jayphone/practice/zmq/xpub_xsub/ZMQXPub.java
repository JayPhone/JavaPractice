package com.jayphone.practice.zmq.xpub_xsub;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * @author JayPhone
 * @description
 * @date 2023/9/12
 */
public class ZMQXPub {
    public static void main(String[] args) throws InterruptedException {
        ZContext context = new ZContext();
        ZMQ.Socket xPubSocket = context.createSocket(SocketType.XPUB);
        xPubSocket.bind("tcp://*:5560");

        for (int i = 0; i < 100000; i++) {
            String topic = "topic1";
            String message = "Message from XPUB";
            xPubSocket.sendMore(topic);
            xPubSocket.send(message);
            Thread.sleep(1000);
        }
    }
}
