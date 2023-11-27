package com.gucardev.springshellexample.command;

import com.gucardev.springshellexample.service.AuthService;
import com.gucardev.springshellexample.util.ShellReader;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;

@Command(group = "Login Commands")
@RequiredArgsConstructor
public class AuthCommand {

  private final ShellReader shellReader;
  private final AuthService authService;

  @Command(command = "login", description = "login method")
  public void login(
      //  @Option(shortNames = 'u') String username, @Option(shortNames = 'p') String password
      ) throws IOException {
    String username = shellReader.readLine("Please enter your username");
    String password = shellReader.readLinePassword("Please enter your password");
    authService.login(username, password);
  }

  @Command(command = "logout", description = "Logout method")
  public void logout() {
    authService.logout();
  }
}
