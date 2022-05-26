package com.flashcards.main.repository;

import com.flashcards.main.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ThemeRepository extends JpaRepository<Theme, Integer> {

    @Modifying
    @Query("update Theme t set t.name = :name where t.id = :id")
    void updateNameById(@Param("id") int id,
                        @Param("name") String name);

    List<Theme> findAllByUserId(int id);

    List<Theme> findAllByCategoryId(int id);
}
