package org.example;

import java.util.ArrayList;
import java.util.List;

public class TodoList {

    private final List<String> tasks = new ArrayList<>();

    public void add(String task) {
        if (task == null) {
            return;
        }
        String normalized = task.trim();
        if (normalized.isEmpty()) {
            return;
        }
        tasks.add(normalized);
    }

    public boolean remove(int index) {
        if (index < 0 || index >= tasks.size()) {
            return false;
        }
        tasks.remove(index);
        return true;
    }

    public List<String> getAll() {
        return new ArrayList<>(tasks);
    }

    public int size() {
        return tasks.size();
    }
}
