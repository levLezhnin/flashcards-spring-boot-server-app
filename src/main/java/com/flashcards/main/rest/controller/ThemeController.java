package com.flashcards.main.rest.controller;

import com.flashcards.main.domain.Theme;
import com.flashcards.main.rest.dto.ThemeDTO;
import com.flashcards.main.service.ThemeService;
import com.flashcards.main.service.UserService;
import com.flashcards.main.rest.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ThemeController {

    private final ThemeService themeService;
    private final UserService userService;

    @PostMapping("/theme/new/")
    public ThemeDTO createNewTheme(@RequestParam String name, @RequestParam int userId, @RequestParam int categoryId) {

        Theme theme = themeService.insert(userId, name, categoryId);

        return ThemeDTO.toDTO(theme);
    }

    @PostMapping("/theme/{id}/")
    public void updateThemeById(@PathVariable int id,
                                @RequestParam String name) {
        themeService.update(id, name);
    }

    @GetMapping("/theme/{theme_id}/user")
    public UserDTO getUserByThemeId(@PathVariable int theme_id) {
        return UserDTO.toDTO(userService.getUserByThemeId(theme_id));
    }

    @GetMapping("/theme/{id}")
    public ThemeDTO getThemeById(@PathVariable int id) {
        return ThemeDTO.toDTO(themeService.getById(id));
    }

    @GetMapping("/user/{id}/themes")
    public List<ThemeDTO> getAllThemesByUserId(@PathVariable int id) {

        return themeService.getAllByUserId(id).stream().map(ThemeDTO::toDTO).collect(Collectors.toList());

    }

    @GetMapping("/category/{id}/themes")
    public List<ThemeDTO> getAllThemesByCategoryId(@PathVariable int id) {

        return themeService.getAllByCategoryId(id).stream().map(ThemeDTO::toDTO).collect(Collectors.toList());

    }

    @DeleteMapping("/theme/{id}")
    public void deleteThemeById(@PathVariable int id) {
        themeService.deleteById(id);
    }
}
