package org.example;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TodoListTest {

    @Test
    void addsRegularTask() {
        TodoList todoList = new TodoList();

        todoList.add("Buy milk");

        assertEquals(1, todoList.size());
        assertEquals("Buy milk", todoList.getAll().get(0).getText());
    }

    @Test
    void trimsSpacesAroundTask() {
        TodoList todoList = new TodoList();

        todoList.add("   Buy milk   ");

        assertEquals("Buy milk", todoList.getAll().get(0).getText());
    }

    @Test
    void ignoresNull() {
        TodoList todoList = new TodoList();

        todoList.add(null);

        assertEquals(0, todoList.size());
    }

    @Test
    void ignoresEmptyString() {
        TodoList todoList = new TodoList();

        todoList.add("");

        assertEquals(0, todoList.size());
    }

    @Test
    void ignoresStringOfSpaces() {
        TodoList todoList = new TodoList();

        todoList.add("    ");

        assertEquals(0, todoList.size());
    }

    @Test
    void removesExistingTask() {
        TodoList todoList = new TodoList();
        todoList.add("First");
        todoList.add("Second");

        boolean removed = todoList.remove(0);

        assertTrue(removed);
        assertEquals(1, todoList.size());
        assertEquals("Second", todoList.getAll().get(0).getText());
    }

    @Test
    void refusesToRemoveWithNegativeIndex() {
        TodoList todoList = new TodoList();
        todoList.add("First");

        assertFalse(todoList.remove(-1));
        assertEquals(1, todoList.size());
    }

    @Test
    void refusesToRemoveWithIndexOutOfBounds() {
        TodoList todoList = new TodoList();
        todoList.add("First");

        assertFalse(todoList.remove(1));
        assertEquals(1, todoList.size());
    }

    @Test
    void emptyListBehavesCorrectly() {
        TodoList todoList = new TodoList();

        assertEquals(0, todoList.size());
        assertTrue(todoList.getAll().isEmpty());
        assertFalse(todoList.remove(0));
        assertFalse(todoList.remove(-1));
        assertFalse(todoList.markDone(0));
    }

    @Test
    void getAllReturnsCopyOfList() {
        TodoList todoList = new TodoList();
        todoList.add("First");

        List<Task> copy = todoList.getAll();
        copy.add(new Task("Injected"));

        assertEquals(List.of("First"),
                todoList.getAll().stream().map(Task::getText).toList());
        assertEquals(1, todoList.size());
    }

    @Test
    void clearRemovesAllTasks() {
        TodoList todoList = new TodoList();
        todoList.add("First");
        todoList.add("Second");

        todoList.clear();

        assertEquals(0, todoList.size());
        assertTrue(todoList.getAll().isEmpty());
    }

    @Test
    void clearOnEmptyListLeavesItEmpty() {
        TodoList todoList = new TodoList();

        todoList.clear();

        assertEquals(0, todoList.size());
        assertTrue(todoList.getAll().isEmpty());
    }

    @Test
    void markDoneMarksTaskAtValidIndex() {
        TodoList todoList = new TodoList();
        todoList.add("First");
        todoList.add("Second");

        boolean marked = todoList.markDone(1);

        assertTrue(marked);
        assertFalse(todoList.getAll().get(0).isDone());
        assertTrue(todoList.getAll().get(1).isDone());
    }

    @Test
    void markDoneWithNegativeIndexReturnsFalse() {
        TodoList todoList = new TodoList();
        todoList.add("First");

        assertFalse(todoList.markDone(-1));
        assertFalse(todoList.getAll().get(0).isDone());
    }

    @Test
    void markDoneWithIndexOutOfBoundsReturnsFalse() {
        TodoList todoList = new TodoList();
        todoList.add("First");

        assertFalse(todoList.markDone(1));
        assertFalse(todoList.getAll().get(0).isDone());
    }

    @Test
    void searchFindsTasksCaseInsensitively() {
        TodoList todoList = new TodoList();
        todoList.add("Buy milk");
        todoList.add("Walk the dog");
        todoList.add("MilK shake");

        List<Task> results = todoList.search("milk");

        assertEquals(2, results.size());
        assertEquals(List.of("Buy milk", "MilK shake"),
                results.stream().map(Task::getText).toList());
    }

    @Test
    void searchReturnsEmptyListWhenNoMatch() {
        TodoList todoList = new TodoList();
        todoList.add("Buy milk");

        List<Task> results = todoList.search("shopping");

        assertTrue(results.isEmpty());
    }

    @Test
    void searchWithEmptyStringReturnsEmptyList() {
        TodoList todoList = new TodoList();
        todoList.add("Buy milk");

        List<Task> results = todoList.search("");

        assertTrue(results.isEmpty());
    }

    @Test
    void searchWithWhitespaceQueryReturnsEmptyList() {
        TodoList todoList = new TodoList();
        todoList.add("Buy milk");

        List<Task> results = todoList.search("   ");

        assertTrue(results.isEmpty());
    }
}