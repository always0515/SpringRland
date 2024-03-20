package kr.co.rland.web.service;

import kr.co.rland.web.entity.Category;
import kr.co.rland.web.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceIMP implements CategoryService {

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> getList() {

        List<Category> list = repository.findAll();

        return list;
    }

}