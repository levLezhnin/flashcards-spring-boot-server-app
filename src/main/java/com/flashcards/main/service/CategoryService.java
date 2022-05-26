package com.flashcards.main.service;

import com.flashcards.main.domain.Category;

import java.util.List;

public interface CategoryService {

    Category insert(String name);

    Category getById(int id);

    Category getByName(String name);

    List<Category> getAll();

    void update(int id, String name);

    void deleteById(int id);
}
