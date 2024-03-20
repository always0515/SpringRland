package kr.co.rland.web.api;

import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;
import kr.co.rland.web.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("apiMenuController")
@RequestMapping("api/menu")
public class MenuController {

    @Autowired
    private MenuService service;

    @GetMapping
    public List<MenuView> list() {

        List<MenuView> list = service.getList(1);

        return list;
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
