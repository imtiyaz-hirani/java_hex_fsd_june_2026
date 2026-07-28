package com.springboot.ecom.service;

import com.springboot.ecom.model.Category;
import com.springboot.ecom.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> getAll() {
       return categoryRepository
                .findAll()
                .stream()
                .sorted((c1,c2)->c1.getSequence() - c2.getSequence())
                .toList();
    }
}
