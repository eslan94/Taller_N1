package com.desarrollo.videojuegos.controllers;

import com.desarrollo.videojuegos.models.User;
import com.desarrollo.videojuegos.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @Autowired
    UserService service;

    @GetMapping("/register")
    public String getFormRegister(Model model, Authentication authentication){
        if(authentication != null && authentication.isAuthenticated()){
            return "redirect:/";
        }
        model.addAttribute("user", new User());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user){
        try{
            this.service.save(user);
            return "redirect:/login";
        }catch (Exception ex){
            return "redirect:/register?duplicate";
        }
    }


}
