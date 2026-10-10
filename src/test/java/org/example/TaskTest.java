package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TaskTest {

    @Test
    void newTaskIsNotDone() {
        Task task = new Task("Buy milk");

        assertEquals("Buy milk", task.getText());
        assertFalse(task.isDone());
    }

    @Test
    void markDoneSetsDoneStatus() {
        Task task = new Task("Buy milk");

        task.markDone();

        assertTrue(task.isDone());
    }

    @Test
    void toStringRendersNotDoneTask() {
        Task task = new Task("Buy milk");

        assertEquals("[ ] Buy milk", task.toString());
    }

    @Test
    void toStringRendersDoneTask() {
        Task task = new Task("Buy milk");
        task.markDone();

        assertEquals("[x] Buy milk", task.toString());
    }
}