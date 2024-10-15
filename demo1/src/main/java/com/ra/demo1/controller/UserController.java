package com.ra.demo1.controller;

import com.ra.demo1.model.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping
    public String home(Model model) {
        return "home";
    }

    // /user?page=0&size=5
    @GetMapping("/users")
    public String users (
        @RequestParam(name = "page",defaultValue = "0") Integer page,
                @RequestParam(name = "size",defaultValue = "3") Integer size,
                        Model model){
        model.addAttribute("users",userService.findAll(page,size));
        model.addAttribute("page",page);
        model.addAttribute("size",size);
        model.addAttribute("totalPages",userService.totalPages(size));
        return "users";
    }
}
