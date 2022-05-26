package com.flashcards.main.service;

import com.flashcards.main.domain.User;

import java.util.List;

public interface UserService {

    User insert(String nickname, String email, String password);

    User getById(int id);

    void update(int id, String nickname, String email, String password);

    void deleteById(int id);

    User getUserByThemeId(int theme_id);

    User getByEmail(String email);

    List<User> getAll();
}
