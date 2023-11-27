package com.gucardev.springshellexample.exception;

import java.util.stream.Collectors;
import org.springframework.shell.ParameterValidationException;
import org.springframework.shell.command.CommandExceptionResolver;
import org.springframework.shell.command.CommandHandlingResult;

public class CLIExceptionResolver implements CommandExceptionResolver {
  @Override
  public CommandHandlingResult resolve(Exception ex) {
    if (ex instanceof ParameterValidationException e) {
      return CommandHandlingResult.of(
          e.getConstraintViolations().stream()
                  .map(violation -> violation.getPropertyPath() + " " + violation.getMessage())
                  .collect(Collectors.joining(". "))
              + '\n');
    }

    return CommandHandlingResult.of(ex.getMessage() + '\n', 1);
  }
}
