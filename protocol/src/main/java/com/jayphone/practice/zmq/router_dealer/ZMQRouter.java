package com.jayphone.practice.zmq.router_dealer;

import org.zeromq.*;


/**
 * @author JayPhone
 * @description Router充当消息的路由器，接收请求
 * @date 2023/9/11
 */
public class ZMQRouter {
    public static void main(String[] args) throws InterruptedException {
        ZContext context = new ZContext();
        ZMQ.Socket routerSocket = context.createSocket(SocketType.ROUTER);
        routerSocket.bind("tcp://*:5559");

        for (int i = 0; i < 100000; i++) {
            //接收并处理消息
            String identity = routerSocket.recvStr();
            String content = routerSocket.recvStr();

            //在路由器上添加一些自定义逻辑，例如根据身份标识来处理消息
            System.out.println("接收来自：" + identity + "的消息：" + content);

            //回复消息给DEALER节点
            routerSocket.sendMore(identity);
            routerSocket.send("回复消息");

            //取出头帧(identity)，取出之后msg长度减1
//            ZMsg msg = ZMsg.recvMsg(routerSocket);
//            ZFrame unwrap = msg.unwrap();
//            System.out.println(unwrap.toString());

            Thread.sleep(1000);
        }
    }
}
