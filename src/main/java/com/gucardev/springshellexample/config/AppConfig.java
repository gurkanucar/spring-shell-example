package com.gucardev.springshellexample.config;

import com.gucardev.springshellexample.exception.CLIExceptionResolver;
import com.gucardev.springshellexample.util.ShellReader;
import org.jline.reader.LineReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.shell.Availability;
import org.springframework.shell.AvailabilityProvider;

@Configuration
public class AppConfig {

  //  @Bean
  //  public BCryptPasswordEncoder bCryptPasswordEncoder() {
  //    return new BCryptPasswordEncoder();
  //  }

  @Bean
  CLIExceptionResolver customExceptionResolver() {
    return new CLIExceptionResolver();
  }

  @Bean
  public ShellReader shellReader(@Lazy LineReader lineReader) {
    return new ShellReader(lineReader);
  }

  @Bean
  public AvailabilityProvider userLoggedProvider() {
    return () -> {
      if (isUserLoggedIn()) {
        return Availability.available();
      } else {
        return Availability.unavailable("You are not logged in.");
      }
    };
  }

  private boolean isUserLoggedIn() {
    return SecurityContextHolder.getContext().getAuthentication() != null
        && SecurityContextHolder.getContext().getAuthentication().isAuthenticated();
  }
}
