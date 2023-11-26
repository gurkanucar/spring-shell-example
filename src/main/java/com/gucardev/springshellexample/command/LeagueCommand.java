package com.gucardev.springshellexample.command;

import com.gucardev.springshellexample.config.TeamResponseFormatter;
import com.gucardev.springshellexample.model.LeagueResponse;
import com.gucardev.springshellexample.remote.LeagueApiClient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@RequiredArgsConstructor
@ShellComponent
public class LeagueCommand {

  private final LeagueApiClient leagueApiClient;
  private final TeamResponseFormatter teamResponseFormatter;

  @ShellMethod(key = "lg -l", value = "get list")
  public void leagueList() {
    List<LeagueResponse> scores =
        Objects.requireNonNull(leagueApiClient.getAllScores().getBody()).result();

    // Print the table
    System.out.println(teamResponseFormatter.coverToTable(scores));
  }

  @ShellMethod(key = "lg team", value = "Get team by name")
  public String team(
      @NotBlank
          @Size(min = 2)
          @ShellOption(
              value = "-t",
              help = "team name..",
              defaultValue = ShellOption.NULL,
              arity = 1)
          String teamName) {
    //      log.info("league response %s".formatted(team));
    return "league response %s".formatted(teamName);
  }
}
