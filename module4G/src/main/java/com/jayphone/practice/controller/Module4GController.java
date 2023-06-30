package com.jayphone.practice.controller;

import com.jayphone.practice.service.Module4GSender;
import com.jayphone.practice.service.Module4GServer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

import static cn.hutool.core.util.CharsetUtil.GBK;

/**
 * @author JayPhone
 * @description
 * @date 2023/6/8
 */
@Slf4j
@RestController()
@RequestMapping("/jy")
public class Module4GController {
    @PostMapping("/sendMsg")
    public String sendMsg() {
        Module4GSender.sendMsg("13048119089", "您好，履安科技祝您生活愉快，谢谢。卓越的电子产品与服务提供商");
        return "success";
    }

    @PostMapping("/call")
    public String call() {
        Module4GSender.sendVoiceCall("13048119089", "您好，履安科技祝您生活愉快，谢谢。卓越的电子产品与服务提供商");
        return "success";
    }
}
