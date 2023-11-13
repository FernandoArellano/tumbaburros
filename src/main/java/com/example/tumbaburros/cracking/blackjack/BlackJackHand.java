package com.example.tumbaburros.cracking.blackjack;

import java.util.ArrayList;
import java.util.List;

public class BlackJackHand extends Hand<BlackJackCard>{

    public int score(){
        List<Integer> scores = possibleScores();
        int minScore=0;
        int maxScore=0;
        for(int score : scores){
            if(score>maxScore&& score<=21){
                maxScore = score;
            } else if(score <=21 && minScore<score) {
                minScore = score;
            }
        }

        return maxScore;
    }

    private List<Integer> possibleScores() {
        List<Integer> scores = new ArrayList<>();
        int minScore=0;
        int maxScore=0;
        for(BlackJackCard card : cards){

            if(card.isAce()){
                if(minScore+card.maxValue()<21){
                    minScore = minScore+card.maxValue();
                } else if (minScore + card.minValue()<21){
                    minScore = minScore + card.minValue();
                }

                if(maxScore+card.maxValue()<21){
                    maxScore = maxScore+card.maxValue();
                } else if (maxScore + card.minValue()<21){
                    maxScore = maxScore + card.minValue();
                }
            } else {
                minScore += card.value();
                maxScore += card.value();
            }
        }
        if(minScore != maxScore){
            scores.add(minScore);
        }
        scores.add(maxScore);
        return scores;
    }


}
