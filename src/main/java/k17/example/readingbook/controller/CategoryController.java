package k17.example.readingbook.controller;

import k17.example.readingbook.entity.Category;
import k17.example.readingbook.model.dto.BookDto;
import k17.example.readingbook.model.request.ParamsCreateBook;
import k17.example.readingbook.model.request.ParamsCreateCate;
import k17.example.readingbook.service.BookService;
import k17.example.readingbook.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    @GetMapping(value="/categories")
    public ResponseEntity<?> getListCategory() {
        List<Category> categories = categoryService.getAllCategory();
        return ResponseEntity.ok(categories);
    }

    @PostMapping("/cate/create")
    public ResponseEntity<?> createBook(@Validated @RequestBody ParamsCreateCate req)  {
        Category cate = categoryService.createCate(req);
        return ResponseEntity.ok(cate);
    }





}
