package com.flashcards.version1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flashcards.main.domain.Theme;
import com.flashcards.main.domain.User;
import com.flashcards.main.rest.controller.UserController;
import com.flashcards.main.rest.dto.UserDTO;
import com.flashcards.main.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@DisplayName("Класс UserControllerTest")
@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    public static final int USER_1_ID = 1;
    public static final int USER_3_ID = 3;
    public static final String USER_3_NICKNAME = "Leva";
    public static final String USER_3_EMAIL = "example3@gmail.com";
    public static final String USER_3_PASSWORD = "12333";
    public static final List<Theme> USER_3_THEMES = new ArrayList<>();

    public static final User USER_3 = new User(
            USER_3_ID,
            USER_3_NICKNAME,
            USER_3_EMAIL,
            USER_3_PASSWORD,
            USER_3_THEMES,
            new ArrayList<>()
    );

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private UserService userService;

    @Test
    void shouldCorrectlyCreateNewUser() throws Exception {

        given(userService.insert(USER_3_NICKNAME, USER_3_EMAIL, USER_3_PASSWORD)).willReturn(USER_3);
        UserDTO expectedResult = UserDTO.toDTO(USER_3);

        mockMvc.perform(post("/user/new/")
                        .param("nickname", USER_3_NICKNAME)
                        .param("email", USER_3_EMAIL)
                        .param("password", USER_3_PASSWORD))
                .andExpect(status().isOk()).andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldCorrectGetUserById() throws Exception {

        given(userService.getById(3)).willReturn(USER_3);
        UserDTO expectedResult = UserDTO.toDTO(USER_3);

        mockMvc.perform(get("/user/" + USER_3_ID))
                .andExpect(status().isOk()).andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldCorrectGetUserByEmail() throws Exception {
        given(userService.getByEmail(USER_3_EMAIL)).willReturn(USER_3);
        UserDTO expectedResult = UserDTO.toDTO(USER_3);

        mockMvc.perform(get("/user/email/").param("email", USER_3_EMAIL))
                .andExpect(status().isOk()).andExpect(content().json(mapper.writeValueAsString(expectedResult)));
    }

    @Test
    void shouldCorrectlyUpdateUserById() throws Exception {
        mockMvc.perform(post("/user/1/")
                .param("newNickname", USER_3_NICKNAME)
                .param("newEmail", USER_3_EMAIL)
                .param("newPassword", USER_3_PASSWORD))
                .andExpect(status().isOk());
        verify(userService, times(1)).update(USER_1_ID, USER_3_NICKNAME, USER_3_EMAIL, USER_3_PASSWORD);
    }

    @Test
    void shouldCorrectlyDeleteUserById() throws Exception {
        mockMvc.perform(delete("/user/"+ USER_3_ID))
                .andExpect(status().isOk());

        verify(userService, times(1)).deleteById(3);
    }

}
