package com.eazybytes.cards.entity;

import jakarta.persistence.*;

@Entity
public class Plastic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    long plasticNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cardId")
    Cards card;
}
