package kr.co.rland.web.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {

    @GetMapping("/signin")
    // @ResponseBody
    public String signin() {
        return "/user/signin";
    }

    @PostMapping("/signin")
    public String signin(String username, String password, HttpSession session) {
        session.setAttribute("test","hello world");
        return "redirect:/index";

    }
}
