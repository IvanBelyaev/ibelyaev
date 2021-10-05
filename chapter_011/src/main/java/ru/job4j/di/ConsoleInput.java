package ru.job4j.di;

import java.io.Console;
import java.util.Scanner;

public class ConsoleInput {
    private Scanner scanner = new Scanner(System.in);

    public String ask() {
        System.out.print("Введите значение: ");
        return scanner.nextLine();
    }
}
