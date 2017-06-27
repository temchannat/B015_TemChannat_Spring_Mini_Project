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


    /**
     * Dashboard or home page
     * Display Total of Users, Total of Male, Total of Female
     *
     * @param modelMap
     * @return
     */
    @RequestMapping({"/dashboard", "/"})
    public String dashboardPage(ModelMap modelMap) {
        int totalUsers = userServiceImplement.countTotalUsers();
        int totalMale = userServiceImplement.countMale();
        int totalFemale = userServiceImplement.countFemale();

        modelMap.addAttribute("TOTALUSERS", totalUsers);
        modelMap.addAttribute("TOTALMALE", totalMale);
        modelMap.addAttribute("TOTALFEMALE", totalFemale);

        return "dashboard";
    }

    /**
     * List all users that status = 1
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/user-list")
    public String userList(ModelMap modelMap) {
        users = userServiceImplement.userList();
        modelMap.addAttribute("USERS", users);
        return "user-list";
    }

    /**
     * User form to input or update
     *
     * @param modelMap
     * @return
     */
    @RequestMapping("/user-cu")
    public String addUser(ModelMap modelMap) {
        modelMap.addAttribute("USER", new User());
        modelMap.addAttribute("saveStatus", true);
        return "user-cu";
    }

    /**
     * Save Users
     *
     * @param user
     * @param modelMap
     * @return
     */
    @PostMapping("/user-save")
    public String userSave(@ModelAttribute("USER") User user, ModelMap modelMap) {
        userServiceImplement.save(user);
        modelMap.addAttribute("USERS", users);
        return "redirect:/user-list";

    }

    /**
     * Delete user, but it is just update users status to ZERO (0)
     *
     * @param userHash
     * @return
     */
    @RequestMapping("/user-delete/{userHash}")
    public String deleteUser(@PathVariable("userHash") String userHash) {
        userServiceImplement.deleteByUserHash(userHash);
        return "redirect:/user-list";
    }


    /**
     * When users click edit, it goes to this method first and then
     * redirect to /user-update
     *
     * @param userHash
     * @param modelMap
     * @return
     */
    @GetMapping("/user-edit/{userHash}")
    public String editUser(@PathVariable("userHash") String userHash, ModelMap modelMap) {
        User user = userServiceImplement.findOneUser(userHash);
        modelMap.addAttribute("USER", user);
        modelMap.addAttribute("saveStatus", false);
        return "user-cu";
    }

    /**
     * Update users and then redirect to /user-list
     *
     * @param user
     * @return
     */
    @PostMapping("/user-update")
    public String userUpdate(@ModelAttribute("user") User user) {
        System.out.println("USER HASH IS " + user.getUserHash());
        userServiceImplement.updateByUserHash(user);
        return "redirect:/user-list";
    }


    /**
     * User Details
     *
     * @param userHash
     * @param model
     * @return
     */
    @RequestMapping("/user-detail/{userHash}")
    public String userDetails(@PathVariable("userHash") String userHash, ModelMap model) {
        User user = userServiceImplement.findOneUser(userHash);
        model.addAttribute("USER", user);
        return "user-detail";
    }


}
