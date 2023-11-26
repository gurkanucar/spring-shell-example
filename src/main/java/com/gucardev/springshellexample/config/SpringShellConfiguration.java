package com.gucardev.springshellexample.config;

import org.jline.utils.AttributedString;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.jline.PromptProvider;

@Configuration
public class SpringShellConfiguration implements PromptProvider {
  @Override
  public final AttributedString getPrompt() {
    return new AttributedString("myapp:> ");
  }
}
