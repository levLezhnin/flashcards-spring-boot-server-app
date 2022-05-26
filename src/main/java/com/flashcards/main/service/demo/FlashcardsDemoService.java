package com.flashcards.main.service.demo;

import com.flashcards.main.domain.Category;
import com.flashcards.main.domain.Theme;
import com.flashcards.main.domain.User;
import com.flashcards.main.domain.UserFavouriteCategories;
import com.flashcards.main.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FlashcardsDemoService {

    private final UserService userService;
    private final ThemeService themeService;
    private final CardService cardService;
    private final CategoryService categoryService;
    private final UserFavouriteCategoriesService userFavouriteCategoriesService;

    @Transactional
    public void UserDemo(){
        userService.insert("Booby",
                        "booby_official22@gmail.com",
                "13490000");

        List<User> all_users = userService.getAll();

        all_users.forEach(System.out::println);

        User user = userService.getById(1);

        List<UserFavouriteCategories> categories1 = user.getFavourite_categories();
        for (UserFavouriteCategories c : categories1) {
            System.out.println(c.getCategory_id());
        }
    }

    @Transactional
    public void ThemeDemo() {
        themeService.insert(1, "Чё-та",2);
        Theme theme2 = themeService.getById(2);
        System.out.println(theme2.getId() + ":");
        System.out.println("Name: " + theme2.getName() + ", User: " + theme2.getUser().getNickname());
        List<Theme> themes_in_category_1 = themeService.getAllByCategoryId(1);
        for (Theme t : themes_in_category_1) {
            System.out.println(t.toString());
        }
    }

    @Transactional
    public void CardDemo() {

        cardService.insert(2, "update", "update");
    }

    @Transactional
    public void CategoryDemo() {
        categoryService.insert("newCategory");
        categoryService.update(2, "updated name");
    }

    @Transactional
    public void UserFavouriteCategories() {
        userFavouriteCategoriesService.insert(1,3);
        List<Category> userFavouriteCategories = userFavouriteCategoriesService.getAllCategoriesByUserId(1);
        System.out.println(userFavouriteCategories);
    }
}
