package com.jayphone.practice.service;

import com.jayphone.practice.handler.BusinessInHandler;
import com.jayphone.practice.handler.BusinessOutHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

/**
 * @author JayPhone
 * @description 4G模块服务
 * @date 2023/6/7
 */
@Slf4j
public class Module4GServer {
    public static final int SERVER_PORT = 8234;

    public void start() {
        //第一个线程组是用于接收Client连接的
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //第二个线程组是用于实际的业务处理操作的
        EventLoopGroup workGroup = new NioEventLoopGroup();
        //创建一个启动NIO服务的辅助启动类ServerBootstrap 就是对我们的Server进行一系列的配置
        ServerBootstrap serverBootstrap;

        try {
            //创建一个启动NIO服务的辅助启动类ServerBootstrap 就是对我们的Server进行一系列的配置
            serverBootstrap = new ServerBootstrap();
            //需要指定使用NioServerSocketChannel这种类型的通道(服务端 -->NioServerSocketChannel)
            serverBootstrap.channel(NioServerSocketChannel.class);
            //绑定两个线程组
            serverBootstrap.group(bossGroup, workGroup);
            /*
             * 服务器端TCP内核模块维护两个队列，我们称之为A,B吧
             * 客户端向服务端connect的时候，会发送带有SYN标志的包(第一次握手)
             * 服务端收到客户端发来的SYN时，向客户端发送SYN ACK确认(第二次握手)
             * 此时TCP内核模块把客户端连接加入到A队列中，最后服务端收到客户端发来的ACK时(第三次握手)
             * TCP内核模块把客户端连接从A队列移到B队列，连接成功，应用程序的accept会返回
             * 也就是说accept从B队列中取出完成三次握手的连接
             * A队列和B队列的长度之和是backLog,当A,B队列的长度之和大于backLog时，新连接将会被TCP内核拒绝
             * 所以，如果backLog过小，可能会出现accept速度跟不上,A,B队列满了，导致新的客户端无法连接，
             * 要注意的是，backLog对程序支持的连接数并无影响，backLog影响的只是还没有被accept取出的连接
             */
            //设置TCP连接的缓冲区
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128);
            //保持连接
            serverBootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);
            //一定要使用childHandler 去绑定具体的事件处理器
            serverBootstrap.childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) {
                    //将自定义的serverHandler加入到管道中去（多个）
                    socketChannel.pipeline()
//                            .addLast(new ServerOutHandler())
//                            .addLast(new ServerSimpleInHandler());
                            .addLast(new StringDecoder(Charset.forName("GBK")))
                            .addLast(new BusinessInHandler())
                            .addLast(new BusinessOutHandler());
                }
            });
            //绑定指定的端口 进行监听
            ChannelFuture channelFuture = serverBootstrap.bind(SERVER_PORT).sync();
            channelFuture.addListener((ChannelFutureListener) future -> {
                if (future.isSuccess()) {
                    log.info("静远4G服务已经在 {} 端口启动...", SERVER_PORT);
                } else {
                    log.info("静远4G服务在 {} 端口启动失败...", SERVER_PORT);
                }
            });
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
