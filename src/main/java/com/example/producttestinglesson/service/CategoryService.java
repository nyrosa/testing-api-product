package com.example.producttestinglesson.service;

import com.example.producttestinglesson.dto.CategoryRequest;
import com.example.producttestinglesson.dto.CategoryResponse;
import com.example.producttestinglesson.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    List<CategoryResponse> findAllCategory();
    CategoryResponse getCategoryByID(Integer id);
    CategoryResponse updateCategory(Integer id, CategoryRequest categoryRequest);
    boolean deleteCategory(int id);
    List<CategoryResponse> searchByName(String name);

}
