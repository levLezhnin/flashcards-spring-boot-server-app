package com.flashcards.main.service;

import com.flashcards.main.domain.Category;
import com.flashcards.main.repository.CategoryRepository;
import com.flashcards.main.repository.UserRepository;
import com.flashcards.main.domain.UserFavouriteCategories;
import com.flashcards.main.repository.UserFavouriteCategoriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFavouriteCategoriesServiceImplementation implements UserFavouriteCategoriesService {

    private final UserFavouriteCategoriesRepository userFavouriteCategoriesRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public UserFavouriteCategories insert(int user_id, int category_id) {
        UserFavouriteCategories userFavouriteCategories = UserFavouriteCategories.builder()
                .user(userRepository.getById(user_id))
                .category_id(category_id)
                .build();
        return userFavouriteCategoriesRepository.save(userFavouriteCategories);
    }

    @Override
    public UserFavouriteCategories getById(int id) {
        return userFavouriteCategoriesRepository.getById(id);
    }

    @Override
    public List<Category> getAllCategoriesByUserId(int user_id) {

        List<Category> categories = new ArrayList<>();

        for (UserFavouriteCategories userFavouriteCategories : userRepository.getById(user_id).getFavourite_categories()) {
            categories.add(categoryRepository.getById(userFavouriteCategories.getCategory_id()));
        }
        return categories;
    }

    @Override
    public List<Integer> getAllCategoryIdsByUserId(int user_id) {
        List<Integer> ids = new ArrayList<>();
        for (UserFavouriteCategories id : userRepository.getById(user_id).getFavourite_categories()) {
            ids.add(id.getCategory_id());
        }
        return ids;
    }

    @Override
    public List<UserFavouriteCategories> getAllByUserId(int user_id) {
        return userRepository.getById(user_id).getFavourite_categories();
    }

    @Override
    public void deleteById(int id) {
        userFavouriteCategoriesRepository.deleteById(id);
    }

}
