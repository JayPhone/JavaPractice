package com.jayphone.practice.entity;

import lombok.Data;

/**
 * @author JayPhone
 * @description
 * @date 2023/7/6
 */
@Data
public class SendResult {
    /**
     * 设备id
     */
    private String deviceId;
    /**
     * 发送状态
     */
    private boolean result;
}
