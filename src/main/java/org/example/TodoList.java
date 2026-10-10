package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TodoList {

    private final List<Task> tasks = new ArrayList<>();

    public void add(String task) {
        if (task == null) {
            return;
        }
        String normalized = task.trim();
        if (normalized.isEmpty()) {
            return;
        }
        tasks.add(new Task(normalized));
    }

    public boolean remove(int index) {
        if (index < 0 || index >= tasks.size()) {
            return false;
        }
        tasks.remove(index);
        return true;
    }

    public void clear() {
        tasks.clear();
    }

    public boolean markDone(int index) {
        if (index < 0 || index >= tasks.size()) {
            return false;
        }
        tasks.get(index).markDone();
        return true;
    }

    public List<Task> search(String query) {
        if (query == null) {
            return List.of();
        }
        String normalized = query.trim();
        if (normalized.isEmpty()) {
            return List.of();
        }
        String lowerQuery = normalized.toLowerCase(Locale.ROOT);
        List<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getText().toLowerCase(Locale.ROOT).contains(lowerQuery)) {
                result.add(task);
            }
        }
        return result;
    }

    public List<Task> getAll() {
        return new ArrayList<>(tasks);
    }

    public int size() {
        return tasks.size();
    }
}