package com.wentnet;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class WentnetApplication {

    public static void main(String[] args) {
        SpringApplication.run(WentnetApplication.class, args);
        log.info("项目启动");
    }

}
