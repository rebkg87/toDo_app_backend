package com.example.todoapp.service;

import com.example.todoapp.model.Task;
import com.example.todoapp.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task(1L, "Test Description", false);
    }

    @Test
    public void testUpdateTask() {
        Long taskId = 1L;
        Task updatedTaskDetails = new Task(1L, "Updated Title", true);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(task));

        when(taskRepository.save(any(Task.class))).thenReturn(updatedTaskDetails);

        Task updatedTask = taskService.updateTask(taskId, updatedTaskDetails);

        assertNotNull(updatedTask);
        assertEquals("Updated Title", updatedTask.getTitle());
        assertTrue(updatedTask.isCompleted());
    }

    @Test
    public void testUpdateTask_NotFound() {
        Long taskId = 999L;
        Task updatedTaskDetails = new Task(1L, "Updated Task", true);

        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            taskService.updateTask(taskId, updatedTaskDetails);
        });
        assertEquals("Task not found", exception.getMessage());
    }
}
