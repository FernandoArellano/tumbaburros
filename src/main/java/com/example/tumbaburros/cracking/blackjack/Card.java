package com.example.tumbaburros.cracking.blackjack;

public abstract class Card {
    protected int faceValue;
    private Suit suit;
    private boolean available = true;

    public int getFaceValue() {
        return faceValue;
    }

    public Suit getSuit() {
        return suit;
    }

    public Card(int faceValue, Suit suit){
        this.faceValue = faceValue;
        this.suit = suit;
    }

    public boolean isAvailable(){
        return available;
    }

    public void markAvailable(){
        available = true;
    }

    public void markUnavailable(){
        available = false;
    }
}
