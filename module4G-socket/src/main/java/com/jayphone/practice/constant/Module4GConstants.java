package com.jayphone.practice.constant;

/**
 * @author JayPhone
 * @description 4G模块常量
 * @date 2023/6/9
 */
public interface Module4GConstants {
    /**
     * 注册包报文
     */
    String REGISTER = "register";

    /**
     * 心跳包报文
     */
    String HEART_BEAT = "heartbeat";

    /**
     * 短信发送成功报文
     */
    String MSG_SEND_SUCCESS = "SMS_SEND_SUCESS";

    /**
     * 短信发送失败报文
     */
    String MSG_SEND_FAILURE = "SMS_SEND_FAIL";

    /**
     * 正在拨打对方电话，未接通状态
     */
    String TTS_CALL = "tts busy";

    /**
     * 电话已接通，正在播放语音
     */
    String TTS_CALL_PLAYING = "TTS speack";

    /**
     * 未接通或挂断
     */
    String TTS_CALL_FAILURE = "NO CARRIER";

    /**
     * 播放完毕，并已经挂断电话
     */
    String TTS_CALL_SUCCESS = "ready";
}
