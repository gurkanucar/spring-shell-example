package com.gucardev.springshellexample.config;

import com.gucardev.springshellexample.exception.CLIExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
  @Bean
  CLIExceptionResolver customExceptionResolver() {
    return new CLIExceptionResolver();
  }
}
