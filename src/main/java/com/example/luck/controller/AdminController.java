//package com.example.luck.controller;
//
//import org.springframework.web.bind.annotation.*;
//import org.springframework.stereotype.Controller;
//import org.springframework.beans.factory.annotation.Autowired;
//import com.example.luck.repositories.TaskRepository;
//import com.example.luck.repositories.UserRepository;
//import com.example.luck.service.TaskService;
//import com.example.luck.service.UserService;
//import org.springframework.ui.Model;
//import org.springframework.security.access.prepost.PreAuthorize;
//
//@Controller
//@RequestMapping("/admin")
//public class AdminController {
//
//    @Autowired
//    private TaskService taskService;
//    @Autowired
//    private UserService userService;
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private TaskRepository taskRepository;
//
//    @GetMapping("/users")
//    public String listUsers(Model model) {
//        model.addAttribute("users", userRepository.findAll());
//        return "admin/users";
//    }
//
//    @GetMapping("/tasks")
//    public String listTasks(Model model) {
//        model.addAttribute("tasks", taskRepository.findAll());
//        return "admin/tasks";
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/admin-tasks")
//    public String viewAllTasks(Model model) {
//        model.addAttribute("tasks", taskService.getAllTasks());
//        return "admin/tasks";  // Admin's task list view
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/admin-users")
//    public String viewAllUsers(Model model) {
//        model.addAttribute("users", userService.getAllUsers());
//        return "admin/users";  // Admin's user list view
//    }
//
////    public String showAllUsers(Model model) {
////        List<User> users = userService.getAllUsers();
////        model.addAttribute("users", users);
////        return "admin/users";
////    }
//
//
//}


//second version

package com.example.luck.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.luck.service.TaskService;
import com.example.luck.service.UserService;
import org.springframework.ui.Model;
import org.springframework.security.access.prepost.PreAuthorize;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')") // Ограничиваем доступ ко всем методам контроллера
public class AdminController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "admin/users"; // View для отображения списка пользователей
    }

    @GetMapping("/tasks")
    public String listTasks(Model model) {
        model.addAttribute("tasks", taskService.getAllTasks());
        return "admin/tasks"; // View для отображения списка задач
    }
}
