package com.example.tumbaburros.cracking.blackjack;

public enum Suit {
    Club (0), Diamond (1) ,Heart(2), Spade(3);

    private int value;

    private Suit(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    public static Suit getSuitFromValue(int value){
        if(value == 0){
            return Suit.Club;
        } else if(value == 1){
            return Suit.Diamond;
        }
        else if(value == 2){
            return Suit.Heart;
        }
        else if(value == 3){
            return Suit.Spade;
        }
        return null;
    }
}
