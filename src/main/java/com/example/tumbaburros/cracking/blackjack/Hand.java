package com.example.tumbaburros.cracking.blackjack;

import java.util.ArrayList;
import java.util.List;

public class Hand <T extends Card>{
    protected List<T> cards = new ArrayList<>();

    public int score(){
        int score =0;
        for(T card : cards){
            score += card.getFaceValue();
        }
        return score;
    }

    public void addCard(T card){
        cards.add(card);
    }
}
