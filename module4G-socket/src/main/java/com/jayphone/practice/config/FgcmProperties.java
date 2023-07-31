package com.jayphone.practice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author JayPhone
 * @description
 * @date 2023/7/31
 */
@ConfigurationProperties(prefix = "fgcm")
@Data
public class FgcmProperties {
    /**
     * 是否启用
     */
    private Boolean enable = true;

    /**
     * TCP服务端口
     */
    private Integer serverPort = 8234;
}
