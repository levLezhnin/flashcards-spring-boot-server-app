package com.flashcards.main.repository;

import com.flashcards.main.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Modifying
    @Query("update Category c set c.name = :name where c.id = :id")
    void update(@Param("id") int id,
                @Param("name") String name);

    Category findByName(String name);

    List<Category> findAll();
}
