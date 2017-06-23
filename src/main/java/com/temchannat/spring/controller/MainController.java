package com.temchannat.spring.controller;

import com.temchannat.spring.model.User;
import com.temchannat.spring.service.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by temchannat on 6/15/17.
 */

@Controller
public class MainController {

    List<User> users = new ArrayList<>();

    UserServiceImplement userServiceImplement;

    @Autowired
    public MainController(UserServiceImplement userServiceImplement) {
        this.userServiceImplement = userServiceImplement;
    }

    @RequestMapping("/user-list")
    public String userList(ModelMap modelMap) {
        users = userServiceImplement.userList();
        modelMap.addAttribute("USERS", users);
        return "user-list";
    }

    @RequestMapping("/user-cu")
    public String addUser(ModelMap modelMap) {
        modelMap.addAttribute("USER", new User());
        modelMap.addAttribute("ADD_STATUS", true);
        return "user-cu";
    }

    @PostMapping("/user-c")
    public String userC(@ModelAttribute("USER") User user, ModelMap modelMap){
        users.add(user);
        modelMap.addAttribute("USERS", users);
        return "redirect:/user-list";

    }




}
