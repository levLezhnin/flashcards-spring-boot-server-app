package com.flashcards.main.service;

import com.flashcards.main.domain.Theme;
import com.flashcards.main.domain.User;
import com.flashcards.main.repository.ThemeRepository;
import com.flashcards.main.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final ThemeRepository themeRepository;

    @Override
    public User insert(String nickname, String email, String password) {
        return userRepository.saveAndFlush(User.builder()
                .nickname(nickname)
                .email(email)
                .password(password)
                .themes(new ArrayList<>())
                .favourite_categories(new ArrayList<>())
                .build());
    }


    @Override
    @Transactional
    public void update(int id, String nickname, String email, String password) {
        User existing_user = getById(id);
        if (nickname == null || nickname.equals("")) {
            nickname = existing_user.getNickname();
        }
        if (email == null || email.equals("")) {
            email = existing_user.getEmail();
        }
        if (password == null || password.equals("")) {
            password = existing_user.getPassword();
        }

        userRepository.updateById(id, nickname, email, password);
    }

    @Override
    public User getById(int id) {
        return userRepository.getById(id);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
        userRepository.flush();
    }

    @Override
    public User getUserByThemeId(int theme_id) {
        Theme theme = themeRepository.getById(theme_id);
        return theme.getUser();
    }
}
