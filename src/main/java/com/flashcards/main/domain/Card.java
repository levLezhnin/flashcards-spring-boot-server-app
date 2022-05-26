package com.flashcards.main.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "CARD")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "KEY")
    private String key;

    @Column(name = "VALUE")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "THEME_ID")
    private Theme theme;

    @Override
    public String toString() {
        return id + ": " + key + ", " + value;
    }
}
