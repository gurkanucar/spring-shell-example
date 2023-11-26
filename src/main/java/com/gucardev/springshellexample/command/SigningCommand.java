package com.gucardev.springshellexample.command;

import com.gucardev.springshellexample.util.ShellReader;
import java.io.Console;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.shell.command.annotation.Command;

@Command(group = "Login Commands")
@RequiredArgsConstructor
public class SigningCommand {

  private final ShellReader shellReader;
  private final AuthenticationManager authenticationManager;

  @Command(command = "login", description = "login method")
  public void login() {
    Console console = System.console();
    String username = console.readLine("Please enter your username: ");
    String password = new String(console.readPassword("Please enter your password: "));
    Authentication request = new UsernamePasswordAuthenticationToken(username, password);
    try {
      Authentication result = authenticationManager.authenticate(request);
      SecurityContextHolder.getContext().setAuthentication(result);
      System.out.println("Successfully authenticated!");
    } catch (AuthenticationException e) {
      System.out.println("Authentication failed: " + e.getMessage());
    }
  }

  @Command(command = "logout", description = "Logout method")
  public void logout() {
    SecurityContextHolder.clearContext();
    System.out.println("You have been logged out.");
  }
}
