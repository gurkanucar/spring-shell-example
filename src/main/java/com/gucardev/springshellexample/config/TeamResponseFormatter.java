package com.gucardev.springshellexample.config;

import com.gucardev.springshellexample.model.LeagueResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;

public final class TeamResponseFormatter {
  public String coverToTable(List<LeagueResponse> teams) {
    var data = teams.stream().map(TeamResponseFormatter::toRow).collect(Collectors.toList());
    data.add(0, addRow("team", "lose", "win", "play", "point"));

    ArrayTableModel model = new ArrayTableModel(data.toArray(Object[][]::new));
    TableBuilder table = new TableBuilder(model);
    table.addHeaderAndVerticalsBorders(BorderStyle.fancy_light);
    return table.build().render(100);
  }

  private static String[] toRow(LeagueResponse c) {
    return addRow("%s - %s".formatted(c.rank(), c.team()), c.lose(), c.win(), c.play(), c.point());
  }

  private static String[] addRow(String team, String lose, String win, String play, String point) {
    return new String[] {team, lose, win, play, point};
  }
}
