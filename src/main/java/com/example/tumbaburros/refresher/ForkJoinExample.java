package com.example.tumbaburros.refresher;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinExample {

    static class SumTask extends RecursiveTask<Long>{

        private static final int THRESHOLD = 1000;
        private int[] array;
        private int start, end;

        public SumTask(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }


        @Override
        protected Long compute() {

            if(end-start<=THRESHOLD){   //end-start to get the real amount of elements from this chunk
                long sum = 0;
                for(int i=start; i<end; i++) sum+=array[i];  //sum from start to sum the right elements in the array
                return sum;

            }else {
                int mid = start + (end-start)/2;
                SumTask leftTask = new SumTask(array, start, mid);
                SumTask rightTask = new SumTask(array, mid, end);

                leftTask.fork();
                long rightResult = rightTask.compute();

                long leftResult = leftTask.join();
                return leftResult + rightResult;
            }
        }
    }

    public static void main(String[] args) {
        int[] data = new int[10000];
        for(int i=0; i<data.length;i++) data[i] = 1;

        ForkJoinPool pool = ForkJoinPool.commonPool();

        long result = pool.invoke(new SumTask(data,0, data.length));
        System.out.println(result);
    }
}
