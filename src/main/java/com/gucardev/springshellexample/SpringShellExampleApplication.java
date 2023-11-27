package com.gucardev.springshellexample;

import org.springframework.beans.BeansException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.shell.command.annotation.CommandScan;

@EnableFeignClients
@SpringBootApplication
@CommandScan
public class SpringShellExampleApplication implements ApplicationContextAware {

  public static void main(String[] args) {
    SpringApplication.run(SpringShellExampleApplication.class, args);
  }

  // it will clear screen | CommandlineRunner or ApplicationRunner did not work here.
  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    if (System.console() != null) {
      System.out.print("\033[H\033[2J");
      System.out.flush();
    } else {
      System.out.println("============================================");
    }
  }
}
