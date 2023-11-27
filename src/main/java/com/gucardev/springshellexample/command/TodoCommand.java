package com.gucardev.springshellexample.command;

import com.gucardev.springshellexample.model.Todo;
import com.gucardev.springshellexample.service.TodoService;
import com.gucardev.springshellexample.util.ShellPrinter;
import com.gucardev.springshellexample.util.ShellReader;
import com.gucardev.springshellexample.util.TodoFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.command.CommandRegistration;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.CommandAvailability;
import org.springframework.shell.command.annotation.Option;

@RequiredArgsConstructor
@Command(group = "Todo Commands")
public class TodoCommand {

  private final TodoService todoService;
  private final TodoFormatter todoFormatter;
  private final ShellReader shellReader;
  private final ShellPrinter printer;

  @CommandAvailability(provider = "userLoggedProvider")
  @Command(command = "todo -l", description = "get list")
  public void getTodos() {
    System.out.println(todoFormatter.coverToTable(todoService.getAllTodos()));
  }

  @CommandAvailability(provider = "userLoggedProvider")
  @Command(command = "todo -c", description = "create new")
  public void addTodo() {
    var todoTitle = shellReader.readLineRequired("Enter todo");
    var isCompleted = shellReader.readLineOptions("did you complete todo?", List.of("y", "n"));
    Todo todo = new Todo();
    todo.setTitle(todoTitle);
    todo.setCompleted(isCompleted.equalsIgnoreCase("Y"));
    todoService.createTodo(todo);
    printer.printInfo("todo added");
  }

  @CommandAvailability(provider = "userLoggedProvider")
  @Command(command = "todo -t", description = "toggle todo status")
  public void toggleTodo(
      @Option(
              shortNames = 'i',
              longNames = "id",
              description = "todo id..",
              arity = CommandRegistration.OptionArity.EXACTLY_ONE)
          Long id) {
    todoService.toggleTodoStatus(id);
    printer.printInfo("todo status changed. " + id);
  }

  @CommandAvailability(provider = "userLoggedProvider")
  @Command(command = "todo -d", description = "delete todo")
  public void deleteTodo(
      @Option(
              shortNames = 'i',
              longNames = "id",
              description = "todo id..",
              arity = CommandRegistration.OptionArity.EXACTLY_ONE)
          Long id) {
    todoService.deleteTodo(id);
    printer.printInfo("todo deleted.");
  }
}
