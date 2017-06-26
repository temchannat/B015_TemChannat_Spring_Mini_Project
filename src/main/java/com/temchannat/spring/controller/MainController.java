package com.temchannat.spring.controller;

import com.temchannat.spring.model.User;
import com.temchannat.spring.service.UserServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        modelMap.addAttribute("saveStatus", true);
        return "user-cu";
    }

    @PostMapping("/user-save")
    public String userSave(@ModelAttribute("USER") User user, ModelMap modelMap) {
        userServiceImplement.save(user);
        modelMap.addAttribute("USERS", users);
        return "redirect:/user-list";

    }


    @RequestMapping("/user-delete/{userHash}")
    public String deleteUser(@PathVariable("userHash") String userHash) {
        userServiceImplement.deleteByUserHash(userHash);
        return "redirect:/user-list";
    }


    @GetMapping("/user-edit/{userHash}")
    public String editUser(@PathVariable("userHash") String userHash, ModelMap modelMap) {
        System.out.println("USER HASH IS " + userHash);
        User user = userServiceImplement.findOneUser(userHash);
        modelMap.addAttribute("USER", user);
        modelMap.addAttribute("saveStatus", false);
        return "user-cu";
    }

    @PostMapping("/user-update")
    public String userUpdate(@ModelAttribute("user") User user) {
        userServiceImplement.updateByUserHash(user);
        return "redirect:/user-list";
    }


    @RequestMapping("/user-detail/{userHash}")
    public String userDetails(@PathVariable("userHash") String userHash, ModelMap model) {
        User user = userServiceImplement.findOneUser(userHash);
        model.addAttribute("USER", user);
        return "user-detail";
    }


}
