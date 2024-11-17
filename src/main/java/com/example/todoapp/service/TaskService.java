package com.example.todoapp.service;

import com.example.todoapp.model.Task;
import com.example.todoapp.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return  taskRepository.findAll();
    }

    public List<Task> getTasksByStatus(boolean completed){
        return  taskRepository.findByCompleted(completed);
    }

    public long countTasksByStatus(boolean completed){
        return taskRepository.countByCompleted(completed);
    }

    public List<Task> searchTaskByTitle(String title) {
        return taskRepository.findByTitle(title);

    }

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        task.setTitle(taskDetails.getTitle());
        task.setCompleted(taskDetails.isCompleted());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
        taskRepository.delete(task);
    }

}
