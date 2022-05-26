package com.flashcards.version1.repository;

import com.flashcards.main.domain.User;
import com.flashcards.main.repository.ThemeRepository;
import com.flashcards.main.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@DataJpaTest
@DisplayName("Класс UserRepositoryTest")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ThemeRepository themeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @DisplayName("should insert user")
    @Test
    void shouldInsertUser(){
        User expected = User.builder()
                .email("new_email123@gmail.com")
                .nickname("new user")
                .password("123234")
                .build();

        userRepository.save(expected);

        User actual = userRepository.getById(4);

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("should update user by id")
    @Test
    void shouldUpdateUserById(){

        User expected = User.builder()
                .id(1)
                .nickname("Goga123")
                .email("example666@gmail.com")
                .password("123456")
                .build();

        userRepository.updateById(1, "Goga123", "example666@gmail.com", "123456");

        User actual = userRepository.getById(1);
        assertThat(actual.getNickname()).isEqualTo(expected.getNickname());
        assertThat(actual.getEmail()).isEqualTo(expected.getEmail());
        assertThat(actual.getPassword()).isEqualTo(expected.getPassword());
    }

    @DisplayName("should get all users")
    @Test
    void shouldGetAllUsers(){

        assertThat(userRepository.findAll().size()).isEqualTo(3);
    }

    @DisplayName("should get a user by id")
    @Test
    void shouldGetUserById(){

        User expected = User.builder()
                .id(1)
                .nickname("Goga")
                .email("example@gmail.com")
                .password("12345")
                .build();

        User actual = userRepository.getById(1);

        assertThat(actual.getNickname()).isEqualTo(expected.getNickname());
        assertThat(actual.getEmail()).isEqualTo(expected.getEmail());
        assertThat(actual.getPassword()).isEqualTo(expected.getPassword());
    }

    @DisplayName("should get a user by email")
    @Test
    void shouldGetUserByEmail(){

        User expected = userRepository.getById(1);

        User actual = userRepository.getByEmail("example@gmail.com");

        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("should delete a user by id")
    @Test
    void shouldDeleteUserById(){

        userRepository.deleteById(1);

        assertThatThrownBy(() -> userRepository.getById(1)).isInstanceOf(JpaObjectRetrievalFailureException.class);
    }


}
