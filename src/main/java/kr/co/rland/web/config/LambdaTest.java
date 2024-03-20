package kr.co.rland.web.config;

import kr.co.rland.web.entity.Menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class LambdaTest {
    public static void main(String[] args) {

        List<Menu>list = new ArrayList<>();

        list.add(Menu.builder().id(3).korName("dkgk").build());
        list.add(Menu.builder().id(5).korName("asd").build());
        list.add(Menu.builder().id(1).korName("bcd").build());

        //인터페이스를 익명클래스로 구현하기
        //객체를 정렬할 때 임시로 사용할 목적 (람다 표현식)
        Comparator<Menu> aaa = new Comparator<Menu>() {
            @Override
            public int compare(Menu o1, Menu o2) {
                return (int)(o1.getId() - o2.getId());
            }
        };
        //1차 줄이기
        Comparator<Menu> aaa1 = (Menu o1, Menu o2)->{
            return (int)(o1.getId() - o2.getId());
        };

        //2차 줄이기
        //오름차 순 정렬
        Comparator<Menu> aaa2 = (o1,o2)->(int)(o1.getId()- o2.getId());
        //내림차 순 정렬
        Comparator<Menu> aaa3 = (o1,o2)->(int)(o2.getId()- o1.getId());
        list.sort(aaa3);
        System.out.println(list);
    }

}
