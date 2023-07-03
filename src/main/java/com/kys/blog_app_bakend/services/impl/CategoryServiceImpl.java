package com.kys.blog_app_bakend.services.impl;

import com.kys.blog_app_bakend.entities.Category;
import com.kys.blog_app_bakend.exceptions.ResourceNotFoundException;
import com.kys.blog_app_bakend.payloads.CategoryDTO;
import com.kys.blog_app_bakend.repositories.CategoryRepository;
import com.kys.blog_app_bakend.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDto) {
        Category category = this.categoryDtoToCategoryEntity(categoryDto);
        Category savedCategory = this.categoryRepository.save(category);
        return this.categoryEntityToCategoryDto(savedCategory);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDto, Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        return this.categoryEntityToCategoryDto(category);
    }

    @Override
    public CategoryDTO getCategoryById(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        return this.categoryEntityToCategoryDto(category);
    }

    @Override
    public List<CategoryDTO> getAllCategories() {
        List<Category> categoryList = this.categoryRepository.findAll();
        return categoryList.stream().map(this::categoryEntityToCategoryDto).collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        this.categoryRepository.delete(category);
    }


    public Category categoryDtoToCategoryEntity(CategoryDTO categoryDto) {
        return this.modelMapper.map(categoryDto, Category.class);
    }

    public CategoryDTO categoryEntityToCategoryDto(Category category) {
        return this.modelMapper.map(category, CategoryDTO.class);
    }
    
}
