package com.yumkoori.mentoring.user.adapter.in.web;

import com.yumkoori.mentoring.user.adapter.in.web.dto.EmailVerificationRequestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth/signup")
public class SignUpViewController {

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/emailForm")
    public String showSignupEmailForm(
            Model model,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String verificationCode,
            @RequestParam(required = false) String nick) {

        model.addAttribute("email", email);
        model.addAttribute("verificationCode", verificationCode);
        model.addAttribute("nick", nick);
        return "signupEmailForm";
    }



}
