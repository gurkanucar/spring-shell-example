package com.gucardev.springshellexample.util;

import com.gucardev.springshellexample.model.Todo;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.shell.table.ArrayTableModel;
import org.springframework.shell.table.BorderStyle;
import org.springframework.shell.table.TableBuilder;
import org.springframework.stereotype.Component;

@Component
public final class TodoFormatter {
  public String coverToTable(List<Todo> todos) {
    var data = todos.stream().map(TodoFormatter::toRow).collect(Collectors.toList());
    data.add(0, new String[] {"id", "title", "completed"});

    ArrayTableModel model = new ArrayTableModel(data.toArray(Object[][]::new));
    TableBuilder table = new TableBuilder(model);
    table.addHeaderAndVerticalsBorders(BorderStyle.fancy_light);
    return table.build().render(100);
  }

  private static String[] toRow(Todo c) {
    return new String[] {
      c.getId().toString(),
      c.getTitle(),
      c.isCompleted() ? "\u00A0".repeat(3) + "yes" : "\u00A0".repeat(3) + "no"
    };
  }
}
