package com.vick7.admin.controller;

import com.vick7.library.dto.AdminDto;
import com.vick7.library.model.Admin;
import com.vick7.library.service.impl.AdminServiceImpl;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    private AdminServiceImpl adminService;
    public LoginController(AdminServiceImpl adminService){
        this.adminService = adminService;
    }
    @GetMapping("/login")
    public String loginForm(){

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("adminDto", new AdminDto());
        return "register";
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(){
        return "forgot-password";

    }

    @PostMapping("/register-new")
    public String addNewAdmin(@Valid @ModelAttribute("adminDto")AdminDto adminDto, BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes
                              ){
        try {
            if (result.hasErrors()){
                model.addAttribute("adminDto", adminDto);
                return "register";
            }
            String username = adminDto.getUsername();
            Admin admin = adminService.findByUsername(username);
            if (admin!=null){
                model.addAttribute("adminDto", adminDto);
                redirectAttributes.addFlashAttribute("message","The email already exists please try a different one!");
                return "register";
            }
            adminService.save(adminDto);
            model.addAttribute("adminDto", adminDto);
            redirectAttributes.addFlashAttribute("message","SignUp successfully!");
        } catch (Exception exception){
            redirectAttributes.addFlashAttribute("message","Oops! Registration failed due to a server error!");
        }
        return "register";
    }
}
