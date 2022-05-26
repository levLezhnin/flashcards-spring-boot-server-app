package com.flashcards.main.repository;

import com.flashcards.main.domain.UserFavouriteCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFavouriteCategoriesRepository extends JpaRepository<UserFavouriteCategories, Integer> {
}
