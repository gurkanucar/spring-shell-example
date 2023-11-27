package com.gucardev.springshellexample.command;

import com.gucardev.springshellexample.model.LeagueResponse;
import com.gucardev.springshellexample.remote.LeagueApiClient;
import com.gucardev.springshellexample.util.ShellPrinter;
import com.gucardev.springshellexample.util.TeamResponseFormatter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

@RequiredArgsConstructor
@Command(group = "League Commands")
public class LeagueCommand {

  private final LeagueApiClient leagueApiClient;
  private final TeamResponseFormatter teamResponseFormatter;
  private final ShellPrinter printer;

  @Command(command = "lg -l", description = "get list")
  public void leagueList() {
    List<LeagueResponse> scores =
        Objects.requireNonNull(leagueApiClient.getAllScores().getBody()).result();
    printer.print(teamResponseFormatter.coverToTable(scores));
  }

  @Command(command = "lg team", description = "Get team by name")
  public void team(
      @NotBlank
          @Size(min = 2)
          @Option(
              shortNames = 't',
              longNames = "name",
              description = "team name..",
              arity = CommandRegistration.OptionArity.EXACTLY_ONE)
          String teamName) {
    printer.print("league response %s".formatted(teamName));
  }
}
