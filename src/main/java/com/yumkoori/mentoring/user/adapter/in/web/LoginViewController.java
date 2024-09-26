package com.yumkoori.mentoring.user.adapter.in.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {

    @GetMapping("/main")
    public String showMainPage() {
        return "main";
    }


    @GetMapping("/auth/login")
    public String login() {
        return "login";
    }



}
