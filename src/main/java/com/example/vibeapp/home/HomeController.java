package com.example.vibeapp.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Hello, Thymeleaf!");
        return "home/home";
    }

    @GetMapping("/api/hello")
    @ResponseBody
    public String hello() {
        return "Hello, Vibe!";
    }
}
