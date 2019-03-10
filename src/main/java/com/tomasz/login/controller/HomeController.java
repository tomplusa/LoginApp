package com.tomasz.login.controller;

import com.tomasz.login.model.User;
import com.tomasz.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    private UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userData",
                "Welcome " +
                        user.getName() + " "
                        + user.getLastName());


        if (user.getRole().getRole().equals("ADMIN")) {
            modelAndView.addObject("users", userService.findAllUsers());
            modelAndView.setViewName("admin");
            return modelAndView;
        }
        modelAndView.setViewName("user");
        return modelAndView;
    }
}
