package com.gucardev.springshellexample.config;

import lombok.RequiredArgsConstructor;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.shell.AvailabilityProvider;
import org.springframework.shell.jline.PromptProvider;

@Configuration
@RequiredArgsConstructor
public class SpringShellConfiguration {

  private final AvailabilityProvider userLoggedInProvider;

  @Bean
  public PromptProvider promptProvider() {
    return () -> {
      AttributedStyle style = determinePromptStyle();
      String promptString = buildPromptString();
      return new AttributedString(promptString, style);
    };
  }

  private AttributedStyle determinePromptStyle() {
    if (userLoggedInProvider.get().isAvailable()) {
      return AttributedStyle.DEFAULT
          .background(AttributedStyle.GREEN)
          .foreground(AttributedStyle.BLACK);
    } else {
      return AttributedStyle.DEFAULT
          .background(AttributedStyle.RED)
          .foreground(AttributedStyle.WHITE);
    }
  }

  private String buildPromptString() {
    String username = getUsername();
    return "myapp" + (username != null ? ":" + username : "") + " > ";
  }

  private String getUsername() {
    if (userLoggedInProvider.get().isAvailable()) {
      return SecurityContextHolder.getContext().getAuthentication().getName();
    }
    return null;
  }
}
