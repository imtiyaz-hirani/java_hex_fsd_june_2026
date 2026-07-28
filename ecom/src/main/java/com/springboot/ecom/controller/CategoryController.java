package com.springboot.ecom.controller;

import com.springboot.ecom.model.Category;
import com.springboot.ecom.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/category")
@CrossOrigin(origins = "http://localhost:5173")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/all")
    public List<Category> getAll(){
        return categoryService.getAll();
    }
}
