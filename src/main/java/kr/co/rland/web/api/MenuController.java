package kr.co.rland.web.api;

import kr.co.rland.web.entity.Category;
import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;
import kr.co.rland.web.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("apiMenuController")
@RequestMapping("api/menus")
public class MenuController {

    @Autowired
    private MenuService service;

    @GetMapping
    public List<MenuView> list(
            @RequestParam(name = "c", required = false) Long categoryId,
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "p", required= false, defaultValue ="1") Integer page) {

        List<MenuView> menus = new ArrayList<>();
        if(categoryId != null)
            menus = service.getList(page,categoryId);
        else if(query != null)
            menus = service.getList(page,query);
        else
            menus = service.getList(page);

//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        return menus;
    }

    @GetMapping("1")
    public Menu get(Long id) {

        return null;
    }

    @PostMapping
    public Menu add(Menu menu) {

        return null;
    }

    @PutMapping
    public Menu eidt(Menu menu) {
        return null;
    }

    @DeleteMapping
    public String delete(Long id) {
        return null;
    }
}
