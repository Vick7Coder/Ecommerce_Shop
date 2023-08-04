package com.vick7.admin.controller;

import com.vick7.library.dto.AdminDto;
import com.vick7.library.model.Admin;
import com.vick7.library.service.impl.AdminServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    private AdminServiceImpl adminService;

    private BCryptPasswordEncoder passwordEncoder;
    public LoginController(AdminServiceImpl adminService, BCryptPasswordEncoder passwordEncoder){
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/login")
    public String loginForm(Model model){
        model.addAttribute("title", "Login");
        return "login";
    }
    @RequestMapping("/index")
    public String home(Model model){
        model.addAttribute("title", "Home");
        return "index";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("title", "Register");
        model.addAttribute("adminDto", new AdminDto());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model){
        model.addAttribute("title", "Forgot Password");
        return "forgot-password";

    }

    @PostMapping("/register-new")
    public String addNewAdmin(@Valid @ModelAttribute("adminDto")AdminDto adminDto, BindingResult result,
                              Model model
                              ){
        try {
            if (result.hasErrors()){
                model.addAttribute("adminDto", adminDto);
                result.toString();
                return "register";
            }
            String username = adminDto.getUsername();
            Admin admin = adminService.findByUsername(username);
            if (admin!=null){
                model.addAttribute("adminDto", adminDto);
                model.addAttribute("emailError","The email already exists please try a different one!");
                return "register";
            }
            if (adminDto.getPassword().equals(adminDto.getRepeatPassword())){
                adminDto.setPassword(passwordEncoder.encode(adminDto.getPassword()));
                adminService.save(adminDto);
                model.addAttribute("adminDto", adminDto);
                model.addAttribute("success","SignUp successfully!");
            } else {
                model.addAttribute("adminDto", adminDto);
                model.addAttribute("passError", "The passwords entered do not match!");
                return "register";
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            model.addAttribute("serverError", "Oops! Registration failed due to a server error!");
        }
        return "register";
    }
}
