package com.flashcards.main.domain;

import lombok.*;

import javax.persistence.*;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USER_FAVOURITE_CATEGORIES")
public class UserFavouriteCategories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "category_id")
    private int category_id;
}
