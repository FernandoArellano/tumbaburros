package com.example.tumbaburros.cracking;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class StackOfPlates {

    List<Stack<String>> stackOfPlates = new LinkedList<>();
    int maxOfPlatesPerStack;

    public StackOfPlates(int maxOfPlatesPerStack) {
        this.maxOfPlatesPerStack = maxOfPlatesPerStack;
        Stack<String> stack = new Stack<>();
        stackOfPlates.add(stack);
    }

    public List<Stack<String>> addPlate(String plate){
        Stack<String> stack = stackOfPlates.get(stackOfPlates.size()-1);
        if(stack.size()>= maxOfPlatesPerStack){
            stack = new Stack<>();
            stackOfPlates.add(stack);
        }

        stack.add(plate);

        return stackOfPlates;
    }

    public List<Stack<String>> removePlate(){
        Stack<String> stack = stackOfPlates.get(stackOfPlates.size()-1);
        if(stack.size()==0 && stackOfPlates.size()==1){
            throw new RuntimeException("There are not plates");
        } else if(stack.size()==0){
            stack = stackOfPlates.get(stackOfPlates.size()-2);
        }

        stack.pop();

        return stackOfPlates;
    }

    @Override
    public String toString() {
        return "StackOfPlates{" +
                "stackOfPlates=" + stackOfPlates +
                '}';
    }
}
