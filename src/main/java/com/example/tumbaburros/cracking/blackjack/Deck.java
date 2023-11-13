package com.example.tumbaburros.cracking.blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Deck <T extends Card>{
    private int dealtIndex=0;
    private List<T> cards = new ArrayList<>();

    public void setDeckOfCards(List<T> cards){
        this.cards = cards;
    }

    public void shuffle(){
        Collections.shuffle(cards);
    }

    public int remainingCards(){
        return cards.size()-dealtIndex;
    }

    public List<T> dealHand(int number){
        if(number>remainingCards()) throw new RuntimeException("Not enough cards");

        List<T> list = cards.stream().limit(number).collect(Collectors.toList());
        cards.remove(list);
        dealtIndex = dealtIndex + list.size();

        System.out.println(list);
        return list;
    }

    public T dealCard(){
        if(remainingCards()==0) throw new RuntimeException("Not enough cards");

        T remove = cards.remove(0);
        System.out.println(remove);
        dealtIndex++;
        return remove;
    }

}
