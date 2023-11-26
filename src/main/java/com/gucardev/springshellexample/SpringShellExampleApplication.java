package com.gucardev.springshellexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class SpringShellExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringShellExampleApplication.class, args);
    }

}
