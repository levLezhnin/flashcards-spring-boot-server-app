package com.flashcards.main.rest.controller;

import com.flashcards.main.domain.User;
import com.flashcards.main.rest.dto.UserDTO;
import com.flashcards.main.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/user/new/")
    public UserDTO createNewUser(
            @RequestParam String nickname,
            @RequestParam String email,
            @RequestParam String password
    ) {
        User user = userService.insert(nickname, email, password);
        return UserDTO.toDTO(user);
    }

    @PostMapping("/user/{id}/")
    public void updateUserById(
            @PathVariable int id,
            @RequestParam String newNickname,
            @RequestParam String newEmail,
            @RequestParam String newPassword
    ) {
        userService.update(id, newNickname, newEmail, newPassword);
    }


    @GetMapping("/user/all")
    public List<UserDTO> getAllUsers() {
        return userService.getAll().stream().map(UserDTO::toDTO).collect(Collectors.toList());
    }

    @GetMapping("/user/{id}")
    public UserDTO getUserById(@PathVariable int id){
        return UserDTO.toDTO(userService.getById(id));
    }

    @GetMapping("/user/email/{email}")
    public UserDTO getUserByEmail(@PathVariable String email) {
        return UserDTO.toDTO(userService.getByEmail(email));
    }

    @DeleteMapping("/user/{id}")
    public void deleteUserById(@PathVariable int id) {
        userService.deleteById(id);
    }
}
