package com.example.producttestinglesson.restControllers;

import com.example.producttestinglesson.dto.CategoryRequest;
import com.example.producttestinglesson.dto.CategoryResponse;
import com.example.producttestinglesson.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryResponse> getAllCategories() {
        return categoryService.findAllCategory();
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategoryById(@PathVariable Integer id) {
        return categoryService.getCategoryByID(id);
    }

    @PostMapping
    public CategoryResponse createCategory(@Valid @RequestBody CategoryRequest request) {
        return categoryService.createCategory(request);
    }

    @PatchMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable Integer id,
                                           @Valid @RequestBody CategoryRequest request) {
        return categoryService.updateCategory(id, request);
    }

    @DeleteMapping("/{id}")
    public boolean deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }

    @PostMapping("/{name}")
    public List<CategoryResponse> searchByName(@RequestParam String name){
        return categoryService.searchByName(name);
    }



}