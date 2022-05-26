package com.flashcards.main.repository;

import com.flashcards.main.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Modifying
    @Query("update User u set u.nickname = :nickname, u.email = :email, u.password = :password where u.id = :id")
    void updateById(@Param("id") int id,
                    @Param("nickname") String nickname,
                    @Param("email") String email,
                    @Param("password") String password);

    User getByEmail(String email);

    List<User> findAll();
}
