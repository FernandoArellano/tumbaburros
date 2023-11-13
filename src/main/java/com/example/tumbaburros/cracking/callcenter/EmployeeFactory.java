package com.example.tumbaburros.cracking.callcenter;

public class EmployeeFactory {

    public static Employee getEmployee(int level, CallHandler callHandler){
        if(level == 0){
            return new Respondant(callHandler);
        } else if(level == 1){
            return new Manager(callHandler);
        } else if(level == 2){
            return new Director(callHandler);
        }
        return null;
    }
}
