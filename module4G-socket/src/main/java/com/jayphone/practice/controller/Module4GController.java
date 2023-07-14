package com.jayphone.practice.controller;

import com.jayphone.practice.entity.SendResult;
import com.jayphone.practice.service.Module4GSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        //18649131727
        SendResult sendResult = Module4GSender.sendMsg("13048119089", "您好，履安科技祝您生活愉快，谢谢。卓越的电子产品与服务提供商");
        if (sendResult.isResult()) {
            return "success";
        } else {
            return "failure";
        }
    }

    @PostMapping("/sendVoice")
    public String call() {
        //18649131727
        //18529372739
        //18824031316
        SendResult sendResult = Module4GSender.sendVoiceCall("13048119089", "您好，履安科技祝您生活愉快，谢谢。卓越的电子产品与服务提供商");
        if (sendResult.isResult()) {
            return "success";
        } else {
            return "failure";
        }
    }
}
