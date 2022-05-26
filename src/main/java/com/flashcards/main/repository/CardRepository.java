package com.flashcards.main.repository;

import com.flashcards.main.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Integer> {

    @Modifying
    @Query("update Card c set c.key = :key, c.value = :value where c.id = :id")
    void updateById(@Param("id") int id,
                    @Param("key") String key,
                    @Param("value") String value);

    List<Card> findAllByThemeId(int id);
}
