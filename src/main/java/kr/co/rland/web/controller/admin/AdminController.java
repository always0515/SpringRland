package kr.co.rland.web.controller.admin;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.rland.web.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private MemberService service;

    @GetMapping("signin")
    public String signin() {

        return "admin/signin";
    }
    @PostMapping("signin")
    public String signin(String username, String password, HttpServletResponse resp) {

        boolean valid = service.validdate(username,password);
        if(!valid) {
            return "redirect:signin?error";
        }
//        session.setAttribute("userid","username");

        Cookie uidCookie = new Cookie("uid",username);
        Cookie usernameCookie = new Cookie("username", password);

        uidCookie.setPath("/");
//        uidCookie.setMaxAge(0);
//        uidCookie.setSecure(false);
//        uidCookie.setHttpOnly(true);

        usernameCookie.setPath("/");
        resp.addCookie(uidCookie);
        resp.addCookie(usernameCookie);

        return "redirect:../admin/index";
    }
}

