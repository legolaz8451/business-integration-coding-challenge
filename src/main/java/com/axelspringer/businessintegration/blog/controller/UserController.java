package com.axelspringer.businessintegration.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.axelspringer.businessintegration.blog.domain.User;
import com.axelspringer.businessintegration.blog.repository.UserRepository;
import com.axelspringer.businessintegration.blog.service.AuthenticationService;
import com.axelspringer.businessintegration.blog.service.UserService;

@Controller
public class UserController {

    private final UserService userService;

    private final AuthenticationService authenticationService;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public String signin(Model model, String error, String logout) {
        if (authenticationService.isAuthenticated()) {
            return "redirect:/";
        }

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "user/login";
    }

    @GetMapping("/signup")
    public String signUp(Model model) {
        if (authenticationService.isAuthenticated()) {
            return "redirect:/blog";
        }

        model.addAttribute("user", new User());
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute User user) {
        userService.createUser(user);

        return "user/login";
    }
}
