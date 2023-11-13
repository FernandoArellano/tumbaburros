package com.example.tumbaburros.cracking.callcenter;

public class Call {
    private Rank rank;
    private Caller caller;
    private Employee handler;

    public Call(Caller caller){
        rank = Rank.Responder;
        this.caller = caller;
    }

    public void setHandler(Employee employee){
        this.handler = employee;
    }

    public void setRank(Rank rank){
        this.rank = rank;
    }

    public Rank getRank() {
        return rank;
    }

    public void sendReply(String msg){
        System.out.println(msg);
    }

    public void disconnectCall(){

    }

    public Rank incrementRank(){
        int newRankValue=0;
        if(rank.getValue()<2){
            newRankValue = rank.getValue()+1;
        } else {
            newRankValue = rank.getValue();
        }
        rank = Rank.getRankFromValue(newRankValue);
        return rank;
    }

    public Caller getCaller() {
        return caller;
    }

    public void setCaller(Caller caller) {
        this.caller = caller;
    }
}
