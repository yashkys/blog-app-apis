package com.kys.blog_app_bakend.services;

import com.kys.blog_app_bakend.payloads.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO createCategory(CategoryDTO category);
    CategoryDTO updateCategory(CategoryDTO category, Integer categoryId);
    CategoryDTO getCategoryById(Integer categoryId);
    List<CategoryDTO> getAllCategories();
    void deleteCategory(Integer categoryId);
}
