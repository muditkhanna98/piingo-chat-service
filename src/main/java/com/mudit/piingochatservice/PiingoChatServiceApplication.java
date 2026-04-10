package com.mudit.piingochatservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PiingoChatServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiingoChatServiceApplication.class, args);
    }

}
