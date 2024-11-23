package com.example.luck.controller;

import com.example.luck.model.Task;
import com.example.luck.repositories.UserRepository;
import com.example.luck.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskService taskService;

    @GetMapping
    public String showUserTasks(Model model, Principal principal) {
        Long userId = getCurrentUserId(principal);
        List<Task> tasks = taskService.getUserTasks(userId);
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

    @GetMapping("/add")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/add";
    }

    @PostMapping("/save")
    public String saveTask(@ModelAttribute Task task, Principal principal) {
        Long userId = getCurrentUserId(principal);
        taskService.saveTask(task, userId);
        return "redirect:/tasks";
    }

    @GetMapping("/edit/{id}")
    public String showEditTaskForm(@PathVariable Long id, Model model, Principal principal) {
        Long userId = getCurrentUserId(principal);  // Получить ID текущего пользователя
        Task task = taskService.getTaskById(id, userId);  // Найти задачу только для данного пользователя
        model.addAttribute("task", task);  // Добавить задачу в модель для отображения в форме
        return "tasks/edit";  // Вернуть шаблон формы редактирования задачи
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task task, Principal principal) {
        Long userId = getCurrentUserId(principal);  // Получить ID текущего пользователя
        taskService.updateTask(id, task, userId);  // Обновить задачу с проверкой пользователя
        return "redirect:/tasks";  // Перенаправить на список задач после обновления
    }


    @GetMapping("/delete/{id}")
    public String deleteTask(@PathVariable Long id, Principal principal) {
        Long userId = getCurrentUserId(principal);
        taskService.deleteTask(id, userId);
        return "redirect:/tasks";
    }

    private Long getCurrentUserId(Principal principal) {
        String email = principal.getName();
        return userRepository.findByEmail(email).getId();
    }

    @GetMapping("/tasks")
    public String showUserTasksPageable(Model model, Pageable pageable, Principal principal) {
        Long userId = getCurrentUserId(principal);
        Page<Task> tasks = taskService.getUserTasksWithPagination(userId, pageable);
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

    @GetMapping("admin/tasks")
    public String showAllTasksPageable(Model model, Pageable pageable) {
        Page<Task> tasks = taskService.getAllTasksWithPagination(pageable);
        model.addAttribute("tasks", tasks);
        return "tasks/list";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/tasks/add")
    public String addTask(@ModelAttribute("task") Task task) {
        taskService.saveTask(task);
        return "redirect:/admin/tasks";
    }


//    @GetMapping("/tasks")
//    public String viewUserTasks(Model model, Principal principal) {
//        Long userId = getCurrentUserId(principal);
//        model.addAttribute("tasks", taskService.getTasksByUserId(userId));
//        return "tasks/task-list"; // Create this template for user's task list view
//    }


}
