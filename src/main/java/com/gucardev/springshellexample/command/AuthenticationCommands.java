package com.gucardev.springshellexample.command;

import jakarta.validation.constraints.Size;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

@Command(group = "Login Commands")
public class AuthenticationCommands {

  @Command(command = "login", description = "Login to get Admin privileges.")
  public String login(
      @Size(min = 8, max = 20, message = "Username must be between {min} and {max} ")
          @Option(required = true)
          String username,
      @Size(min = 10, message = "Password must be at least  {min} chars long")
          @Option(required = true)
          String password) {
    //                    loginService.login(username,password);
    return "You are logged in now!";
  }

  @Command(command = "logout", description = "logout as an Admin.")
  public String logout() {
    //        loginService.logout();
    return "You have been logged out.";
  }
}
