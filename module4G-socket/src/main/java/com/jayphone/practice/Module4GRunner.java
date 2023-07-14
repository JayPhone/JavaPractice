package com.jayphone.practice;

import com.jayphone.practice.service.Module4GServer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author JayPhone
 * @description
 * @date 2023/7/5
 */
@Component
public class Module4GRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        Module4GServer.getInstance().start();
    }
}
