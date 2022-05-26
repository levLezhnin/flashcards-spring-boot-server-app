package com.flashcards.main.service;

import com.flashcards.main.domain.Category;
import com.flashcards.main.repository.CategoryRepository;
import com.flashcards.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImplementation implements CategoryService {

    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Category insert(String name) {

        Category category = Category.builder()
                .name(name)
                .themes_in_category(new ArrayList<>())
                .build();

        return categoryRepository.saveAndFlush(category);
    }

    @Override
    public Category getById(int id) {
        return categoryRepository.getById(id);
    }

    @Override
    public Category getByName(String name) {
        return categoryRepository.findByName(name);
    }

    @Override
    public List<Category> getAll() {
        categoryRepository.flush();
        return categoryRepository.findAll();
    }

    @Override
    @Transactional
    public void update(int id, String name) {
        categoryRepository.update(id, name);
    }

    @Override
    public void deleteById(int id) {
        categoryRepository.deleteById(id);
        categoryRepository.flush();
    }
}
