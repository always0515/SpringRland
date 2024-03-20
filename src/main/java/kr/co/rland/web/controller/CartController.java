package kr.co.rland.web.controller;

import com.google.gson.Gson;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("cart")
@Controller
public class CartController {

    @Autowired
    private MenuService menuService;


    @GetMapping("list")
    public String list(Model model, @CookieValue String menus) {
        List<Menu> menuList;

        if (menus == null)
            menuList = new ArrayList<>();
        else {
            String menuStr = URLDecoder.decode(menus,Charset.forName("utf-8"));
            menuList = new Gson().fromJson(menuStr, List.class);
        }
        model.addAttribute(menus, menuList);
        return "/cart/list";
    }
    @PostMapping("add-menu")
    public String addMenu(HttpServletResponse resp, Long id, @CookieValue(required = false) String menus) {

        List<Menu> menuList;
        {
            if (menus==null)
                menuList = new ArrayList<>();
            else {
                String menusStr = URLDecoder.decode(menus, Charset.forName("utf-8"));
                menuList = new Gson().fromJson(menusStr, List.class);
                System.out.println(menusStr);
            }
            Menu menu = menuService.getById(id);
            menuList.add(menu);
        }
        {
            String menusStr = new Gson().toJson(menuList);
            String menuEncoded ="";

            try {
                menuEncoded = URLEncoder.encode(menusStr, "utf-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
            Cookie menusCookie = new Cookie("menus", menuEncoded);
            menusCookie.setPath("/");
            resp.addCookie(menusCookie);
        }


        return "redirect:/menu/list";
    }
}
