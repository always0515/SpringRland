package kr.co.rland.web.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.rland.web.entity.Category;
import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;
import kr.co.rland.web.service.CategoryService;
import kr.co.rland.web.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService service;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/detail")
    // @ResponseBody
    public String detail(
            Model model,
            @RequestParam(name ="id") Long id) {
            Menu menu =service.get(id);
            model.addAttribute("menu",menu);

        return "/menu/detail";
    }

    @GetMapping("list")
    public String list(
            HttpServletResponse resp, @CookieValue(name="menus", required = false) String menusCookie,
            Model model,
            @RequestParam(name = "c", required = false) Long categoryId,
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "p", required= false, defaultValue ="1") Integer page ) {

        int count = 0 ;
        List<MenuView> list = new ArrayList<>();
        if(categoryId != null){
            list = service.getList(page,categoryId);
            count = service.getCount(categoryId);
        }else if(query != null){
            list = service.getList(page,query);
            count = service.getCount(query);
        }else
            list=service.getList(page);
            count = service.getCount();

        List<Category> categoryList = categoryService.getList();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("list", list);
        model.addAttribute("count",count);

        int cartTotalPrice = 0;
        int cartCount = 0;

        List<Menu> menuList = new ArrayList<>();

        if (menusCookie != null) {
            //항상 디코딩 먼저 생각해야한다.
//            System.out.println(menusCookie);
//           String menusStr = URLDecoder.decode(menusCookie, StandardCharsets.UTF_8);
            //(x)틀린방법 : List<Menu> list = new Gson().fromJson(menusCookie, list.class);
            //방법.1 Menu[] list = new Gson().fromJson(menusCookie,Menu[].class);
           Type type = new TypeToken<List<Menu>>(){}.getType();
            menuList = new Gson().fromJson(menusCookie, type);
            for (Menu menu : menuList) {
               cartTotalPrice += menu.getPrice();
               cartCount += 1 ;
            }
        }

        model.addAttribute("cartTotalPrice", cartTotalPrice);
        model.addAttribute("cartCount", cartCount);
        return "/menu/list";
    }
}