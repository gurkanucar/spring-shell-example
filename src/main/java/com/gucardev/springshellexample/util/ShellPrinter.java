package com.gucardev.springshellexample.util;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.springframework.stereotype.Component;

@Component
public class ShellPrinter {

    public void print(String message) {
        System.out.println(message);
    }

    public void print(String message, Ansi.Color color) {
        AnsiConsole.systemInstall();
        System.out.println(Ansi.ansi().fg(color).a(message).reset());
        AnsiConsole.systemUninstall();
    }

    public void printSuccess(String message) {
        print(message, Ansi.Color.GREEN);
    }

    public void printWarning(String message) {
        print(message, Ansi.Color.YELLOW);
    }

    public void printError(String message) {
        print(message, Ansi.Color.RED);
    }

    public void printInfo(String message) {
        print(message, Ansi.Color.CYAN);
    }
}
