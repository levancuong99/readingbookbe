package k17.example.readingbook.service;

import k17.example.readingbook.entity.Category;
import k17.example.readingbook.model.request.ParamsCreateCate;
import k17.example.readingbook.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements  CategoryService{


    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAllBy();
    }

    @Override
    public Category createCate(ParamsCreateCate param) {
        Category cate=new Category();
        cate.setCateName(param.getCateName());
        categoryRepository.save(cate);
        return cate;
    }
}
