package com.temchannat.spring.controller;

import com.temchannat.spring.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by temchannat on 6/15/17.
 */

@Controller
public class MainController {

//    @RequestMapping({"/", "/dashboard"})
//    public String homePage() {
//        return "dashboard";
//    }

//    @RequestMapping("/user-list")
//    public String userListPage() {
//        return "user-list";
//    }

//    @RequestMapping("/role-list")
//    public String roleListPage() {
//        return "role-list";
//    }
//
//

    @RequestMapping("/user-cu")
    public String userCUPage(ModelMap model) {
        model.addAttribute("USER", new User());
        return "user-cu";
    }

    @RequestMapping("/user-c")
    @ResponseBody
    public User userC(@ModelAttribute User user) {
        return user;
    }


    @RequestMapping("/role-cu")
    public String roleCUPage(ModelMap model) {
        model.addAttribute("USER", new User());
        return "role-cu";
    }

    @RequestMapping("/role-c")
    @ResponseBody
    public User roleC(@ModelAttribute User user) {
        return user;
    }


}
