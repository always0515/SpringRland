package kr.co.rland.web.repository;

import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuRepository {

    List<MenuView> findAll(Long categoryId, String query, int offset, int size);

    Menu findById(long id);

    void save(Menu menu);

    void update(Menu menu);

    void delete(long id);

    int count(Long categoryId,String query);

}