package com.gucardev.springshellexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.shell.command.annotation.CommandScan;

@EnableFeignClients
@SpringBootApplication
// @EnableCommand({TodoCommand.class})
@CommandScan
public class SpringShellExampleApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringShellExampleApplication.class, args);
  }
}
