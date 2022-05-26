package com.flashcards.main.rest.controller;

import com.flashcards.main.domain.Category;
import com.flashcards.main.rest.dto.CategoryDTO;
import com.flashcards.main.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping("/category/new/")
    public CategoryDTO createNewCategory(
            @RequestParam String name
    ) {
        Category category = categoryService.insert(name);

        return CategoryDTO.toDTO(category);
    }

    @PostMapping("/category/{id}/")
    public void updateCategoryById(@PathVariable int id,
                                   @RequestParam String name) {
        categoryService.update(id, name);
    }

    @GetMapping("/category/{id}")
    public CategoryDTO getCategoryById(@PathVariable int id) {
        return CategoryDTO.toDTO(categoryService.getById(id));
    }

    @GetMapping("/category/all")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAll().stream().map(CategoryDTO :: toDTO).collect(Collectors.toList());
    }

    @GetMapping("/category/name/")
    public CategoryDTO getCategoryByName(@RequestParam String title) {
        return CategoryDTO.toDTO(categoryService.getByName(title));
    }

    @DeleteMapping("/category/{id}")
    public void deleteCategoryById(@PathVariable int id){
        categoryService.deleteById(id);
    }
}
