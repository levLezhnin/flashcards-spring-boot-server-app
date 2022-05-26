package com.flashcards.main.service;

import com.flashcards.main.domain.Theme;

import java.util.List;

public interface ThemeService {

    Theme insert(int user_id, String name, int category_id);

    void update(int id, String name);

    Theme getById(int id);

    List<Theme> getAllByUserId(int id);

    List<Theme> getAllByCategoryId(int category_id);

    void deleteById(int id);
}
