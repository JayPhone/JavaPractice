package com.jayphone.practice.zmq.req_rep;


import org.zeromq.SocketType;
import org.zeromq.ZContext;
import org.zeromq.ZMQ;

/**
 * @author JayPhone
 * @description send之后必须要recv之后才能继续send，保证整个request/response的流程走完
 * @date 2023/9/8
 */
public class ZMQRequest {
    public static void main(String[] args) throws InterruptedException {
        ZContext context = new ZContext();
        ZMQ.Socket socket = context.createSocket(SocketType.REQ);

        socket.connect("tcp://127.0.0.1:5555");

        for (int i = 0; i < 100; i++) {
            String req = "hello";
            socket.send(req);
            String response = socket.recvStr();
            System.out.println("接收服务端send: " + response);
            Thread.sleep(1000);
        }
    }
}
