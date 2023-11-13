package com.example.tumbaburros.cracking.callcenter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CallHandler {

    List<List<Employee>> allEmployees = new LinkedList<>();

    List<List<Call>> callsQueue = new LinkedList<>();

   public void fillAllEmployees(){
       int NUM_RESPONDENTS = 10;
       int NUM_MANAGERS = 4;
       int NUM_DIRECTORS = 2;

       List<Employee> respondents = new ArrayList<>();
       List<Employee> managers = new ArrayList<>();
       List<Employee> directors = new ArrayList<>();


       fillEmployees(respondents,0, NUM_RESPONDENTS);

       fillEmployees(managers,1, NUM_MANAGERS);

       fillEmployees(directors,2, NUM_DIRECTORS);

       allEmployees.add(respondents);
       allEmployees.add(managers);
       allEmployees.add(directors);
   }

   public void fillEmployees(List<Employee> list, int level, int count){
        for(int i=0; i<count; i++){
            list.add(EmployeeFactory.getEmployee(level, this));
        }
   }

   public Employee getHandlerForCall(Call call){
       Rank rank = call.getRank();

       List<Employee> employeesOfRank = allEmployees.get(rank.getValue());
       for(Employee employee : employeesOfRank){
           if(employee.isFree()){
               return employee;
           }
       }
       return null;
   }

   public void dispatchCall(Caller caller) throws InterruptedException {
       Call call = new Call(caller);

       dispatchCall(call);
   }

    public void dispatchCall(Call call) throws InterruptedException {
       Employee employee = getHandlerForCall(call);

       if(employee == null){
           call.sendReply("Wait for next agent please");
           callsQueue.get(call.getRank().getValue()).add(call);
       } else {
           employee.assignNewCall(call);
           call.setHandler(employee);

           if(new Random().nextInt(10)/3 ==0){
               Thread.sleep(3000);
                employee.escalateAndReassign();
           } else{
               Thread.sleep(2000);
               employee.callCompleted();
           }

       }


    }

}
