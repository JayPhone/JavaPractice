package com.jayphone.practice;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * @author JayPhone
 * @description
 * @date 2023/7/10
 */
public class TestMqtt {
    public static void main(String[] args) {
        MqttClient mqttClient;
        try {
            mqttClient = new MqttClient("tcp://192.168.2.99:1883", "123456", new MemoryPersistence());
            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName("admin");
            options.setPassword("lvan2020".toCharArray());
            options.setConnectionTimeout(30);
            options.setKeepAliveInterval(30);
            options.setCleanSession(true);
            mqttClient.setCallback(new MqttCallbackExtended() {
                @Override
                public void connectComplete(boolean b, String s) {
                    System.out.println("连接成功");
                }

                @Override
                public void connectionLost(Throwable throwable) {
                    System.out.println("连接断开" + throwable);
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    System.out.println("消息到达 " + s + " " + mqttMessage.toString());
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("消息发布成功");
                }
            });
            mqttClient.connect(options);
            mqttClient.subscribe(new String[]{"$share/dem/data/judge/point", "$share/dem/data/judge/device", "$share/dem/data/alarm"},
                    new int[]{1, 1, 1});
        } catch (MqttException e) {
            e.printStackTrace();
        }

    }
}
