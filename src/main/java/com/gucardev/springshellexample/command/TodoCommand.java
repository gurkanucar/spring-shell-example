package com.gucardev.springshellexample.command;

import com.gucardev.springshellexample.util.TodoFormatter;
import com.gucardev.springshellexample.model.Todo;
import com.gucardev.springshellexample.service.TodoService;
import com.gucardev.springshellexample.util.ShellReader;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@RequiredArgsConstructor
@ShellComponent
public class TodoCommand {

  private final TodoService todoService;
  private final TodoFormatter todoFormatter;
  private final ShellReader shellReader;

  @ShellMethod(key = "todo -l", value = "get list")
  public void getTodos() {
    System.out.println(todoFormatter.coverToTable(todoService.getAllTodos()));
  }

  @ShellMethod(key = "todo -c", value = "create new")
  public void addTodo() {
    var todoTitle = shellReader.readLineRequired("Enter todo");
    var isCompleted = shellReader.readLineOptions("did you complete todo?", List.of("y", "n"));
    Todo todo = new Todo();
    todo.setTitle(todoTitle);
    todo.setCompleted(isCompleted.equalsIgnoreCase("Y"));
    todoService.createTodo(todo);
    System.out.println("todo added.");
  }

  @ShellMethod(key = "todo -t", value = "toggle todo status")
  public String toggleTodo(
      @ShellOption(value = "-i", help = "todo id..", defaultValue = ShellOption.NULL, arity = 1)
          Long id) {
    todoService.toggleTodoStatus(id);
    return "todo status changed. " + id;
  }

  @ShellMethod(key = "todo -d", value = "delete todo")
  public String deleteTodo(
      @ShellOption(value = "-i", help = "todo id..", defaultValue = ShellOption.NULL, arity = 1)
          Long id) {
    todoService.deleteTodo(id);
    return "todo deleted.";
  }
}
