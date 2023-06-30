package com.jayphone.practice;

import com.jayphone.practice.service.Module4GServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author JayPhone
 * @description
 * @date 2023/6/8
 */
@Component
public class NettyRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Module4GServer module4GServer = new Module4GServer();
        module4GServer.start();
    }
}
