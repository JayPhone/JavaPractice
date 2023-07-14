package com.jayphone.practice.service;

import com.jayphone.practice.entity.SendResult;
import com.jayphone.practice.util.RegexUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.charset.Charset;

import static com.jayphone.practice.constant.Module4GConstants.*;
import static com.jayphone.practice.constant.Module4GConstants.TTS_CALL_SUCCESS;

/**
 * @author JayPhone
 * @description
 * @date 2023/7/5
 */
@Slf4j
public class SocketThread extends Thread {
    private final Socket socket;
    private OutputStream writer;
    private InputStream reader;

    private String deviceId;

    private final Object lock = new Object();

    //短信超时时间
    public static final int MSG_TIMEOUT = 20 * 1000;
    //电话超时时间
    public static final int VOICE_TIMEOUT = 60 * 1000;

    //短信发送状态
    private volatile boolean msgSendStatus = false;
    //电话拨打状态
    private volatile boolean voiceSendStatus = false;
    //电话是否拨打成功
    private volatile boolean voiceCall = false;

    public SocketThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        super.run();
        try {
            writer = socket.getOutputStream();
            reader = socket.getInputStream();
            byte[] bytes = new byte[128];
            int length;
            while ((length = reader.read(bytes)) > 0) {
                String data = new String(bytes, 0, length);
                log.info("接收设备{}消息：{}", socket.getRemoteSocketAddress(), data);
                handleReceive(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
                reader.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void closeSocket() {
        try {
            if (writer != null) {
                writer.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public SendResult sendMsg(String phone, String msg) {
        if (writer == null) {
            return null;
        }

        synchronized (lock) {
            msgSendStatus = false;
            String data = "86" + phone + ":0:" + msg;
            try {
                writer.write(data.getBytes(Charset.forName("GBK")));
                writer.flush();
                printWriterData(data);
                lock.wait(MSG_TIMEOUT);

                SendResult sendResult = new SendResult();
                sendResult.setDeviceId(deviceId);
                sendResult.setResult(msgSendStatus);
                return sendResult;
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    public SendResult sendVoice(String phone, String msg) {
        if (writer == null) {
            return null;
        }

        synchronized (lock) {
            voiceSendStatus = false;
            voiceCall = false;
            String data = phone + ":3:" + msg;
            try {
                writer.write(data.getBytes(Charset.forName("GBK")));
                writer.flush();
                printWriterData(data);
                lock.wait(VOICE_TIMEOUT);
                //判断电话是否拨打成功，如果没接通则主动挂断
                if (voiceCall && !voiceSendStatus) {
                    sendHangUp();
                }
                SendResult sendResult = new SendResult();
                sendResult.setDeviceId(deviceId);
                sendResult.setResult(voiceSendStatus);
                return sendResult;
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private void sendGetDeviceId() {
        if (writer == null) {
            return;
        }
        try {
            String data = "AT+GSN\r\n";
            writer.write(data.getBytes(Charset.forName("GBK")));
            writer.flush();
            printWriterData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 挂断电话
     */
    private void sendHangUp() {
        if (writer == null) {
            return;
        }
        try {
            String data = "ATH\r\n";
            writer.write(data.getBytes(Charset.forName("GBK")));
            writer.flush();
            printWriterData(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printWriterData(String data) {
        log.info("向设备{}发送命令：{}", socket.getRemoteSocketAddress(), data);
    }

    private void handleReceive(String data) {
        SocketAddress ipAddress = socket.getRemoteSocketAddress();
        //接收注册包
        if (REGISTER.equals(data)) {
            log.info("设备{} 发送注册包", ipAddress);
            sendGetDeviceId();
        }

        //接收心跳包
        if (HEART_BEAT.equals(data)) {
            log.info("设备{} 发送心跳包", ipAddress);
        }

        //接收设备id
        if (RegexUtil.isMatched(RegexUtil.DEVICE_ID_PATTERN, data)) {
            deviceId = data.substring(2, 17);
            log.info("设备id：{} ", deviceId);
        }

        //发送短信成功
        if (data.contains(MSG_SEND_SUCCESS)) {
            log.info("设备{} 短信发送成功", ipAddress);
            synchronized (lock) {
                msgSendStatus = true;
                lock.notifyAll();
            }
        }

        //发送短信失败
        if (data.contains(MSG_SEND_FAILURE)) {
            log.info("设备{} 短信发送失败", ipAddress);
            synchronized (lock) {
                msgSendStatus = false;
                lock.notifyAll();
            }
        }

        //拨打电话,未接通
        if (data.contains(TTS_CALL)) {
            log.info("设备{} 正在拨打对方电话，未接通状态", ipAddress);
            synchronized (lock) {
                voiceCall = true;
            }
        }

        //电话拨打成功，正在播放语音
        if (data.contains(TTS_CALL_PLAYING)) {
            log.info("设备{} 电话已接通，正在播放语音", ipAddress);
            synchronized (lock) {
                voiceSendStatus = true;
            }
        }

        //电话未接通或挂断
        if (data.contains(TTS_CALL_FAILURE)) {
            log.info("设备{} 电话未接通或挂断", ipAddress);
        }

        //语音播放完毕，并已经挂断电话
        if (data.contains(TTS_CALL_SUCCESS)) {
            log.info("设备{} 播放完毕，并已经挂断电话", ipAddress);
            synchronized (lock) {
                lock.notifyAll();
            }
        }
    }
}
