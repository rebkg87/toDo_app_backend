package com.example.todoapp.repository;

import com.example.todoapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository <Task, Long> {
    List<Task> findByCompleted(boolean completed);
    List<Task> findByTitle(String title);
    long countByCompleted(boolean completed);
}
