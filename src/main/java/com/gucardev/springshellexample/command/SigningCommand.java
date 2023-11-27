package com.gucardev.springshellexample.command;

import com.gucardev.springshellexample.util.ShellReader;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
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
  public void login(
      //  @Option(shortNames = 'u') String username, @Option(shortNames = 'p') String password
      ) throws IOException {
    String username = shellReader.readLine("Please enter your username");
    String password = shellReader.readLinePassword("Please enter your password");
    Authentication request = new UsernamePasswordAuthenticationToken(username, password);
    try {
      Authentication result = authenticationManager.authenticate(request);
      SecurityContextHolder.getContext().setAuthentication(result);
      printColored("Successfully authenticated!", Ansi.Color.GREEN);
    } catch (AuthenticationException e) {
      printColored("Authentication failed: " + e.getMessage(), Ansi.Color.RED);
    }
  }

  @Command(command = "logout", description = "Logout method")
  public void logout() {
    SecurityContextHolder.clearContext();
    System.out.println("You have been logged out.");
  }

  private void printColored(String message, Ansi.Color color) {
    AnsiConsole.systemInstall();
    System.out.println(Ansi.ansi().fg(color).a(message).reset());
    AnsiConsole.systemUninstall();
  }
}
