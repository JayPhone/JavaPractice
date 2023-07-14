package com.jayphone.practice.service;

import com.jayphone.practice.entity.SendResult;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;

/**
 * @author JayPhone
 * @description 4G模块发送器
 * @date 2023/6/9
 */
@Slf4j
public class Module4GSender {
    @Resource
    private Module4GServer module4GServer;
    /**
     * 发送短信
     *
     * @param phone
     * @param msg
     * @return
     */
    public static SendResult sendMsg(String phone, String msg) {
        log.info("开始发送短信");
        SendResult result = Module4GServer.getInstance().sendMsg(phone, msg);
        log.info("结束发送短信");
        return result;
    }

    /**
     * 发送语音电话
     *
     * @param phone
     * @param msg
     * @return
     */
    public static SendResult sendVoiceCall(String phone, String msg) {
        log.info("开始发送语音");
        SendResult result = Module4GServer.getInstance().sendVoice(phone, msg);
        log.info("结束发送语音");
        return result;
    }
}
