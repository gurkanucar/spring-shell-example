package com.gucardev.springshellexample.config;

import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.shell.jline.PromptProvider;

@Configuration
public class SpringShellConfiguration {

  @Bean
  public PromptProvider promptProvider() {
    return () -> {
      AttributedStyle style = determinePromptStyle();
      String promptString = buildPromptString();
      return new AttributedString(promptString, style);
    };
  }

  private AttributedStyle determinePromptStyle() {
    if (SecurityContextHolder.getContext().getAuthentication() != null
        && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
      return AttributedStyle.DEFAULT.background(AttributedStyle.GREEN).foreground(AttributedStyle.BLACK);
    } else {
      return AttributedStyle.DEFAULT.background(AttributedStyle.RED).foreground(AttributedStyle.WHITE);
    }
  }

  private String buildPromptString() {
    String username = getUsername();
    return "myapp" + (username != null ? ":" + username : "") + " > ";
  }

  private String getUsername() {
    if (SecurityContextHolder.getContext().getAuthentication() != null
        && SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
      return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    return null;
  }
}
