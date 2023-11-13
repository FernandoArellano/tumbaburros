package com.example.tumbaburros.cracking.callcenter;

import com.example.tumbaburros.cracking.blackjack.Suit;

public enum Rank {
    Responder (0), Manager (1),Director (2);

    private int value;

    Rank(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static Rank getRankFromValue(int value){
        if(value == 0){
            return Rank.Responder;
        } else if(value == 1){
            return Rank.Manager;
        }
        else if(value == 2){
            return Rank.Director;
        }

        return null;
    }


}
