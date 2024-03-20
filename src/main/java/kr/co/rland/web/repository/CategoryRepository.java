package kr.co.rland.web.repository;

import kr.co.rland.web.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryRepository {

    List<Category> findAll();
    Category findById(long id);

    void save(Category category);
    void update(Category category);
    void delete(long id);
}