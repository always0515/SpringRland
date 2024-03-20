package kr.co.rland.web.controller;

import kr.co.rland.web.entity.Category;
import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;
import kr.co.rland.web.service.CategoryService;
import kr.co.rland.web.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/list")
    public String list(
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
        return "/menu/list";
    }
}