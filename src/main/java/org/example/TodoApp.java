package org.example;

import java.util.List;
import java.util.Scanner;

public class TodoApp {

    public static void main(String[] args) {
        TodoList todoList = new TodoList();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Commands: add <task>, remove <index>, list, clear, done <index>, search <text>, exit");

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
                    List<Task> tasks = todoList.getAll();
                    if (tasks.isEmpty()) {
                        System.out.println("The list is empty.");
                    } else {
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + ". " + tasks.get(i));
                        }
                    }
                }
                case "clear" -> {
                    todoList.clear();
                    System.out.println("All tasks removed.");
                }
                case "done" -> {
                    if (argument.isEmpty()) {
                        System.out.println("Usage: done <index>");
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
                    if (todoList.markDone(index - 1)) {
                        System.out.println("Task marked as done.");
                    } else {
                        System.out.println("No task with index " + index + ".");
                    }
                }
                case "search" -> {
                    if (argument.isEmpty()) {
                        System.out.println("Usage: search <text>");
                        continue;
                    }
                    List<Task> results = todoList.search(argument);
                    if (results.isEmpty()) {
                        System.out.println("No matches.");
                    } else {
                        for (int i = 0; i < results.size(); i++) {
                            System.out.println((i + 1) + ". " + results.get(i));
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