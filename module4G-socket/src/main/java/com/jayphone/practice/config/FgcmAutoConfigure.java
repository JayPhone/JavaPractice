package com.jayphone.practice.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author JayPhone
 * @description
 * @date 2023/7/31
 */
@Configuration
@EnableConfigurationProperties(FgcmProperties.class)
public class FgcmAutoConfigure {

}
