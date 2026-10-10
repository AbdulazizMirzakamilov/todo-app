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
        assertEquals(List.of("Buy milk"), todoList.getAll());
    }

    @Test
    void trimsSpacesAroundTask() {
        TodoList todoList = new TodoList();

        todoList.add("   Buy milk   ");

        assertEquals(List.of("Buy milk"), todoList.getAll());
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
        assertEquals(List.of("Second"), todoList.getAll());
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
    }

    @Test
    void getAllReturnsCopyOfList() {
        TodoList todoList = new TodoList();
        todoList.add("First");

        List<String> copy = todoList.getAll();
        copy.add("Injected");

        assertEquals(List.of("First"), todoList.getAll());
        assertEquals(1, todoList.size());
    }
}
