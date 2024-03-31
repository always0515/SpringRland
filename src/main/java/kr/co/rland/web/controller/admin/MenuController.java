package kr.co.rland.web.controller.admin;

import kr.co.rland.web.entity.Menu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("admin/menu")
@Controller("adminMenuController")
public class MenuController {

    @GetMapping("list")
    public String list() {

        return "admin/menu/list";
    }

    @PostMapping("reg")
    public String reg(Menu menu, @RequestParam("img-file")MultipartFile imgFile) {

//        Menu menu= Menu.builder()
//                .korName(korName)
//                .engName(engName)
//                .img(imgFile)
//                .price(price)
//                .build();

        String fileName = imgFile.getOriginalFilename();
        System.out.println(fileName);
        System.out.println(menu);

        return "redirect:list";
    }
    @GetMapping("reg")
    public String reg() {

        return "admin/menu/reg";
    }
}
