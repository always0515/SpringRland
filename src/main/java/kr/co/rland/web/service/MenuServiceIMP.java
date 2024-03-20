package kr.co.rland.web.service;

import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;
import kr.co.rland.web.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceIMP implements MenuService{

    @Autowired
    private MenuRepository repository;

    @Override
    public List<MenuView> getList(Integer page) {

        return getList(null,null,page);
    }

    @Override
    public List<MenuView> getList(Integer page, Long categoryId) {

        return getList(categoryId,null,page);
    }

    @Override
    public List<MenuView> getList(Integer page, String query) {
        return getList(null,query,page);
    }

    @Override
    public int getCount(Long categoryId) {
        return repository.count(categoryId,null);
    }

    @Override
    public int getCount(String query) {
        return repository.count(null,query);
    }

    @Override
    public int getCount() {
        return repository.count(null,null);
    }

    @Override
    public Menu get(Long id) {
        return repository.findById(id);
    }

    @Override
    public Menu getById(long id) {

        return repository.findById(id);
    }

    public List<MenuView> getList(Long categoryId, String query, Integer page) {

        int size = 6;
        int offset = (page-1)*size;
        List<MenuView> list = repository.findAll(categoryId,query,offset,size);
        return list;
    }
}

