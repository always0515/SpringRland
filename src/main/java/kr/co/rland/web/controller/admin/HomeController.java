package kr.co.rland.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller("adminHomeController")
@RequestMapping("admin")
public class HomeController {

    @GetMapping("index")
    public String index(@CookieValue(required = false) String uid, Principal principal) {

        //시큐리티 콘텍스트를 통해 유저정보 얻기
        //방법 1
//        SecurityContext context = SecurityContextHolder.getContext();
//        Authentication authentication = context.getAuthentication();
//        String username = authentication.getName();
//        System.out.println(username);
//        //방법 2
//        String username1 = principal.getName();
//        System.out.println(username1);

//        if(session.getAttribute("userid") == null)
//            if(uid==null)
//                return "redirect:signin";
        System.out.println(uid);
        return "admin/index";
    }
}
