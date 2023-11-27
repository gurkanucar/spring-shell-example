package com.gucardev.springshellexample.command;

import com.gucardev.springshellexample.util.ShellPrinter;
import com.gucardev.springshellexample.util.ShellReader;
import java.io.IOException;
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
  private final ShellPrinter printer;

  @Command(command = "login", description = "login method")
  public void login(
      //  @Option(shortNames = 'u') String username, @Option(shortNames = 'p') String password
      ) throws IOException {
    String username = shellReader.readLine("Please enter your username");
    String password = shellReader.readLinePassword("Please enter your password");
    Authentication request = new UsernamePasswordAuthenticationToken(username, password);
    try {
      Authentication result = authenticationManager.authenticate(request);
      SecurityContextHolder.getContext().setAuthentication(result);
      printer.printSuccess("Successfully authenticated!");
    } catch (AuthenticationException e) {
      printer.printError("Authentication failed: " + e.getMessage());
    }
  }

  @Command(command = "logout", description = "Logout method")
  public void logout() {
    SecurityContextHolder.clearContext();
    printer.printInfo("You have been logged out.");
  }
}
