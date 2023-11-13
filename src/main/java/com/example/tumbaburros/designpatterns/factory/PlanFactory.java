package com.example.tumbaburros.designpatterns.factory;

public class PlanFactory {

    public Plan getPlan(String planType){
        if(planType == null){
            return null;
        } else if(planType.equalsIgnoreCase("domestic")){
            return new DomesticPlan();
        } else if(planType.equalsIgnoreCase("commercial")){
            return new CommercialPlan();
        } else if(planType.equalsIgnoreCase("institucional")){
            return new InstitutionalPlan();
        }
        return null;
    }
}
