package com.gucardev.springshellexample.command;

import com.gucardev.springshellexample.util.ShellReader;
import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
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
  public void login() throws IOException {
    String username = readInput("Please enter your username: ");
    String password = readPassword("Please enter your password: ");
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

  private String readPassword(String prompt) throws IOException {
    Console console = System.console();
    if (console == null) {
      return readInput(prompt);
    } else {
      return new String(console.readPassword(prompt));
    }
  }

  private String readInput(String prompt) throws IOException {
    System.out.print(prompt);
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    return reader.readLine();
  }
}
