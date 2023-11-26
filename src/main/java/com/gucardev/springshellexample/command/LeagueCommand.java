package com.gucardev.springshellexample.command;

import com.gucardev.springshellexample.remote.LeagueApiClient;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@RequiredArgsConstructor
@ShellComponent
@Slf4j
public class LeagueCommand {

  private final LeagueApiClient leagueApiClient;

  @ShellMethod(key = "lg -l", value = "get list")
  public void leagueList() {
    log.info("league response {}", leagueApiClient.getAllScores().getBody());
  }

  @ShellMethod(key = "lg team", value = "Get team by name")
  public void team(
      @Size(min = 2)
          @ShellOption(
              value = "-t",
              help = "team name..",
              defaultValue = ShellOption.NULL,
              arity = 1)
          String team) {
    if (team != null) {
      log.info("league response {}", team);
    }
  }
}
