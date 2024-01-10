package com.example.tumbaburros.collections;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class QueueTest {

    public static void main(String[] args) {
        Queue<Integer> queue = new ArrayBlockingQueue<>(2);
        System.out.println(queue.peek());  //no exception if empty, returns null. retrieve but does not remove
        System.out.println(queue.poll());  //no exception if empty, returns null. retrieves and remove
        System.out.println(queue.element());  //exception if empty retrieve but does not remove
        System.out.println(queue.remove());  //exception if empty retrieve and remove
        queue.offer(1);
        System.out.println(queue);
        queue.offer(2);
        System.out.println(queue);
        queue.offer(3);
        System.out.println(queue);
    }

}
