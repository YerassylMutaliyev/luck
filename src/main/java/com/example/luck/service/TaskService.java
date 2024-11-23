package com.example.luck.service;

import com.example.luck.exception.UnauthorizedAccessException;
import com.example.luck.model.Task;
import com.example.luck.model.User;
import com.example.luck.repositories.TaskRepository;
import com.example.luck.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public Task saveTask(Task task, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser(user);
        return taskRepository.save(task);
    }

    public List<Task> getUserTasks(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return taskRepository.findByUser(user);
    }

    public Task getTaskById(Long taskId, Long userId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
        if (!task.getUser().getId().equals(userId)) {
            throw new UnauthorizedAccessException("You are not authorized to view this task");
        }
        return task;
    }

    public void deleteTask(Long taskId, Long userId) {
        Task task = getTaskById(taskId, userId);
        taskRepository.delete(task);
    }

    public Page<Task> getUserTasksWithPagination(Long userId, Pageable pageable) {
        return taskRepository.findByUserId(userId, pageable);
    }

    public void updateTask(Long taskId, Task updatedTask, Long userId) {
        Task existingTask = getTaskById(taskId, userId);  // Получить существующую задачу с проверкой
        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDueDate(updatedTask.getDueDate());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setCategory(updatedTask.getCategory());
        existingTask.setPriority(updatedTask.getPriority());
        taskRepository.save(existingTask);  // Сохранить изменения
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByUserId(Long userId) {
        return taskRepository.findByUserId(userId);  // Call the repository method
    }

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Page<Task> getAllTasksWithPagination(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(() -> new RuntimeException("Task not found"));
    }



}

