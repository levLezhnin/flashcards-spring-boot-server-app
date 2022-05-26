package com.flashcards.main.service;

import com.flashcards.main.domain.Theme;
import com.flashcards.main.repository.CategoryRepository;
import com.flashcards.main.repository.ThemeRepository;
import com.flashcards.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ThemeServiceImplementation implements ThemeService {

    private final ThemeRepository themeRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Theme insert(int user_id, String name, int category_id) {
        Theme theme = Theme.builder()
                .name(name)
                .user(userRepository.getById(user_id))
                .category(categoryRepository.getById(category_id))
                .cards(new ArrayList<>())
                .build();
        return themeRepository.saveAndFlush(theme);
    }

    @Override
    @Transactional
    public void update(int id, String name) {
        themeRepository.updateNameById(id, name);
    }

    @Override
    @Transactional
    public Theme getById(int id) {
        return themeRepository.getById(id);
    }

    @Override
    public List<Theme> getAllByUserId(int id) {
        return userRepository.getById(id).getThemes();
    }

    @Override
    public List<Theme> getAllByCategoryId(int category_id) {
        return categoryRepository.getById(category_id).getThemes_in_category();
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        themeRepository.deleteById(id);
        themeRepository.flush();
    }
}
