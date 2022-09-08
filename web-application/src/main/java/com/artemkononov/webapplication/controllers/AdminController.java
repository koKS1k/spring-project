package com.artemkononov.webapplication.controllers;

import com.artemkononov.webapplication.entities.Employee;
import com.artemkononov.webapplication.entities.security.Role;
import com.artemkononov.webapplication.entities.security.User;
import com.artemkononov.webapplication.services.security.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService)
    {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String userList(Model model)
    {
        model.addAttribute("allUsers", userService.allUsers());
        return "admin";
    }

    @RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
    public String  deleteUser(@PathVariable Long id,
                              Model model)
    {
          userService.deleteUser(id);

        return "redirect:/admin";
    }

    @RequestMapping(value = "/admin/edit/{id}", method = RequestMethod.GET)
    public String  editUser(Model model, @PathVariable("id") Long id)
    {
        User user=userService.findUserById(id);
        List<Role> allRoles = userService.allRoles();

        model.addAttribute("user", user);
        model.addAttribute("allRoles", allRoles);
        model.addAttribute("action","edit");
        return "user-form";
    }

    @RequestMapping(value = "/admin/edit", method = RequestMethod.POST)
    public String  editUser(@ModelAttribute User user)
    {
        System.out.println(userService.editUser(user));
        return "redirect:/admin";
    }


}
