package com.jayphone.practice.zmq.req_rep;

import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * @author JayPhone
 * @description
 * @date 2023/9/8
 */
public class ZMQResponse {
    public static void main(String[] args) throws InterruptedException {
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.REP);
        socket.bind("tcp://*:5555");
        //必须要要request端返回数据
        while (!Thread.currentThread().isInterrupted()) {
            String request = socket.recvStr();
            System.out.println("接收客户端send: " + request);
            String response = "world";
            socket.send(response);
            Thread.sleep(1000);
        }
    }
}
