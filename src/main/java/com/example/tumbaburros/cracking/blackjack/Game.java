package com.example.tumbaburros.cracking.blackjack;


import java.util.ArrayList;
import java.util.List;

public class Game {
    static Deck<BlackJackCard> deck = new Deck<>();

    public static void main(String[] args) {
        List<BlackJackCard> blackJackCardList = new ArrayList<>();
        for(Suit suit: Suit.values()){
            for(int i=1;i<=13;i++){
                BlackJackCard card = new BlackJackCard(i, suit);
                blackJackCardList.add(card);
            }
        }

        deck.setDeckOfCards(blackJackCardList);
        deck.shuffle();

        List<BlackJackCard> cards = deck.dealHand(2);
        BlackJackHand blackJackHand = new BlackJackHand();

        for(BlackJackCard card: cards){
            blackJackHand.addCard(card);
        }
        System.out.println(blackJackHand.score());

        if(blackJackHand.score()<21){
            blackJackHand.addCard(deck.dealCard());
            System.out.println(blackJackHand.score());
        }


    }
}
