package org.example;

import java.util.List;
import java.util.Scanner;

public class TodoApp {

    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Commands: add <task>, remove <index>, list, exit");

        while (true) {
            System.out.print("> ");
            if (!scanner.hasNextLine()) {
                break;
            }

            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                continue;
            }

            String command;
            String argument;
            int spaceIndex = input.indexOf(' ');
            if (spaceIndex == -1) {
                command = input;
                argument = "";
            } else {
                command = input.substring(0, spaceIndex);
                argument = input.substring(spaceIndex + 1).trim();
            }

            switch (command) {
                case "add" -> {
                    if (argument.isEmpty()) {
                        System.out.println("Usage: add <task>");
                    } else {
                        todoList.add(argument);
                        System.out.println("Task added.");
                    }
                }
                case "remove" -> {
                    if (argument.isEmpty()) {
                        System.out.println("Usage: remove <index>");
                        continue;
                    }
                    int index;
                    try {
                        index = Integer.parseInt(argument);
                    } catch (NumberFormatException e) {
                        System.out.println("Index must be a number.");
                        continue;
                    }
                    if (index < 1) {
                        System.out.println("Index must be 1 or greater.");
                        continue;
                    }
                    if (todoList.remove(index - 1)) {
                        System.out.println("Task removed.");
                    } else {
                        System.out.println("No task with index " + index + ".");
                    }
                }
                case "list" -> {
                    List<String> tasks = todoList.getAll();
                    if (tasks.isEmpty()) {
                        System.out.println("The list is empty.");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                    }
                }
                case "exit" -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Unknown command: " + command);
            }
        }
    }
}
