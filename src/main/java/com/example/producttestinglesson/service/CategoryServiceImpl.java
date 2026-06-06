package com.example.producttestinglesson.service;

import com.example.producttestinglesson.dto.CategoryRequest;
import com.example.producttestinglesson.dto.CategoryResponse;
import com.example.producttestinglesson.entity.Category;
import com.example.producttestinglesson.repositores.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    private Integer nextId = 10;

    private Category mapToEntity(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        category.setDescription(request.description());
        category.setIsActive(request.isActive());
        return category;
    }

    private CategoryResponse mapToResponse(Category category) {
        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription(),
                category.getIsActive()
        );
    }

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        var category = mapToEntity(categoryRequest);
        category.setId(nextId++);
        return mapToResponse(categoryRepository.createCategory(category));
    }

    @Override
    public List<CategoryResponse> findAllCategory() {
        return categoryRepository.getAllProduct()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CategoryResponse getCategoryByID(Integer id) {
        var category = categoryRepository.findCategoryByID(id);
        return mapToResponse(category);
    }

    @Override
    public CategoryResponse updateCategory(Integer id, CategoryRequest categoryRequest) {
        var existingCategory = categoryRepository.findCategoryByID(id);
        if (categoryRequest.name() != null) {
            existingCategory.setName(categoryRequest.name());
        }
        if (categoryRequest.description() != null) {
            existingCategory.setDescription(categoryRequest.description());
        }
        existingCategory.setIsActive(categoryRequest.isActive());
        categoryRepository.updateCategory(existingCategory);
        return mapToResponse(existingCategory);
    }

    @Override
    public boolean deleteCategory(int id) {
        boolean deleted = categoryRepository.deleteCategoryById(id);
        if (!deleted) {
            log.info("Category with id {} not found", id);
        }
        return deleted;
    }

    @Override
    public List<CategoryResponse> searchByName(String name) {
        return categoryRepository.searchByName(name)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
}