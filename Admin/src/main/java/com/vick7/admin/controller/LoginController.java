package com.vick7.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String loginForm(){

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(){
        return "forgot-password";

    }
}
