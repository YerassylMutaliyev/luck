package com.example.luck.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.luck.repositories.UserRepository;
import org.springframework.ui.Model;
import java.security.Principal;
import com.example.luck.model.User;

@Controller
@RequestMapping("/user")
public class UserProfileController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());
        model.addAttribute("user", user);
        return "user/profile";
    }

    @PostMapping("/update")
    public String updateProfile(@ModelAttribute User user, Principal principal) {
        User currentUser = userRepository.findByEmail(principal.getName());
        currentUser.setFullname(user.getFullname());
        currentUser.setEmail(user.getEmail());
        userRepository.save(currentUser);
        return "redirect:/user/profile";
    }
}

