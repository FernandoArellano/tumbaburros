package com.example.tumbaburros.refresher;

public class RecursionTests {
    public static void main(String[] args) {

        System.out.println(sumElements(new int[]{1,2,3,4},0));
        System.out.println(sumAlternatingElements(new int[]{5,3,2,1}, 0));
        System.out.println(multiplyWithSum(7, 4));
        System.out.println(maxRecursive(new int[]{3,9,2,7,30},0));
        System.out.println(countPairs(new int[]{2,5,6,7,8}, 0));
        System.out.println(reverseNumber(1234,0));
        System.out.println(digitSum(1234, 0));
        System.out.println(finder(new int[]{1,3,5,7}, 0,5));
        System.out.println(fibonacci(9));
    }

    //Suma de todos los elementos
    //array [1,2,3,4]
    //result 10
    public static int sumElements(int[] array, int i){

        if(i== array.length) return 0;

        return array[i] + sumElements(array, i+1);

    }

    //suma alternando resta
    //[5,3,2,1]
    //result 5 - 3 + 2 - 1 = 3
    public static int sumAlternatingElements(int[] array, int i){

        if(array.length==i) return 0;

        if(i%2==0){
            return array[i]+ sumAlternatingElements(array, i+1);
        } else {
            return - array[i] + sumAlternatingElements(array, i+1);
        }

    }

    //Multiplicación recursiva (sin usar *)
    //a=3,b=4
    //result 12
    public static int multiplyWithSum(int a, int b){
        if(b==0) return 0;
        return a + (multiplyWithSum(a, b-1));
    }

    //Máximo en un arreglo
    //[3,9,2,7]
    //result 9
    public static int maxRecursive(int[] array, int i){

        if(i==array.length-1) return array[i];

        int next = maxRecursive(array, i+1);

        return next > array[i] ? next : array[i];

    }



    //Contar pares
    //[2,5,6,7,8]
    //result 3
    public static int countPairs(int[] array, int i){
        if(i==array.length) return 0;

        if(array[i]%2==0){
            return 1+ countPairs(array, i+1);
        } else {
            return countPairs(array, i+1);
        }
    }

    //invertir numero
    //1234
    //result 4321
    public static int reverseNumber(int n, int res){
        if(n==0) return res;

        return reverseNumber(n/10, res * 10 + n % 10);
    }

    //suma de digitos
    //1234
    //result 10
    public static int digitSum(int number, int i){

        if(number == 0) return i;

        return digitSum(number/10, i + number %10);

    }

    //Buscar elemento (tipo boolean)
    //1,3,5,7
    //target 5
    //result true
    public static boolean finder(int[] array, int i, int target){
        if(i== array.length) return false;

        if(array[i]==target)return true;

        return finder(array, i+1, target);
    }

    //fibonacci of 9
    //0,1,1,2,3, 5, 8, 13, 21, 34
    public static int fibonacci(int n){
        if(n<=1){
            return n;
        }

        return fibonacci(n-1)+ fibonacci(n-2);

    }

}
