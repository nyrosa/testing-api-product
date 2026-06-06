package com.example.producttestinglesson.repositores;

import com.example.producttestinglesson.entity.Category;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class CategoryRepository {

    private final List<Category> categoryList = new ArrayList<>() {{
        add(new Category(9876, "Car", "Mercedes", true));
        add(new Category(678, "Food", "Watermelon", false));
        add(new Category(52932, "Business", "Product-Sales", false));
        add(new Category(5087, "Drink", "Matcha", true));
    }};

    public List<Category> getAllProduct() {
        return categoryList;
    }

    public Category createCategory(Category category) {
        categoryList.add(category);
        return category;
    }

    public Category findCategoryByID(Integer id) {
        return categoryList.stream()
                .filter(category -> category.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Category with ID = " + id + " is not Found"));
    }

    public boolean deleteCategoryById(Integer id) {
        return categoryList.removeIf(category -> category.getId().equals(id));
    }

    public Category updateCategory(Category updatedCategory) {
        for (int i = 0; i < categoryList.size(); i++) {
            var category = categoryList.get(i);
            if (category.getId().equals(updatedCategory.getId())) {
                categoryList.set(i, updatedCategory);
                return updatedCategory;
            }
        }
        return null;
    }

    public List<Category> searchByName (String name) {
        return categoryList.stream()
                .filter(category -> category.getName()
                        .toLowerCase()
                        .contains(name.toLowerCase()))
                .toList();
    }
}