package k17.example.readingbook.service;

import k17.example.readingbook.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> getAllCategory();
}
