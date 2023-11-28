package com.gucardev.springshellexample.command;

import com.gucardev.springshellexample.model.League;
import com.gucardev.springshellexample.remote.LeagueApiClient;
import com.gucardev.springshellexample.util.ShellPrinter;
import com.gucardev.springshellexample.util.TeamResponseFormatter;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.annotation.Command;

@RequiredArgsConstructor
@Command(group = "League Commands")
public class LeagueCommand {

  private final LeagueApiClient leagueApiClient;
  private final TeamResponseFormatter teamResponseFormatter;
  private final ShellPrinter printer;

  @Command(command = "league -l", description = "get list")
  public void leagueList() {
    List<League> scores = Objects.requireNonNull(leagueApiClient.getAllScores().getBody()).result();
    printer.print(teamResponseFormatter.coverToTable(scores));
  }
}
