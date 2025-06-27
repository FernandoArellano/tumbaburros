package com.example.tumbaburros.exercizes;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void line(){
        System.out.println("-------------------------------------------------------------------------------------");
    }

//    Dado: Lista de palabras
//    Objetivo: Agrupar anagramas entre sí.
//
//    java
//            Copiar
//    Editar
//    List<String> input = List.of("eat", "tea", "tan", "ate", "nat", "bat");
//    Salida esperada:
//
//    java
//            Copiar
//    Editar
//[
//        [eat, tea, ate],
//            [tan, nat],
//            [bat]
//            ]

    public static void groupAnagram(){
        List<String> input = List.of("eat", "tea", "tan", "ate", "nat", "bat");
        Map<String, Set<String>> map = new LinkedHashMap<>();

        for(String s: input){

            char[] array = s.toCharArray();
            Arrays.sort(array);
            String ordered = new String(array);

            if(map.containsKey(ordered)){
                Set set = map.get(ordered);
                set.add(s);
            } else{
                Set set = new LinkedHashSet();
                set.add(s);
                map.put(ordered, set);
            }
        }

        if(!map.isEmpty()){
            Set<String> finalResult = new LinkedHashSet<>();
            for(Map.Entry<String, Set<String>> entry: map.entrySet()){
                Set<String> entryValue = entry.getValue();
                String resultString = entryValue.stream().collect(Collectors.joining(", "));
                finalResult.add(resultString);
            }

            for(String s : finalResult){
                System.out.println("["+s+"]");
            }

        }

    }

//    2. Stream Word Frequency (★★★)
//    Dado: Stream<String> de millones de palabras
//    Objetivo: Obtener las k palabras más frecuentes.
    public static void moreFrequentWord(Stream<String> stream, int k){
        Map<String, Long> frequence = stream.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        LinkedHashSet<String> set = frequence.entrySet().stream()
                .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
                .limit(k)
                .map(entry->entry.getKey())
                .collect(Collectors.toCollection(LinkedHashSet::new));

        System.out.println(set);
    }

//    3. Longest Consecutive Sequence (★★★★)
//    Dado: Array de enteros no ordenado
//    Objetivo: Encontrar la longitud de la subsecuencia consecutiva más larga.
//
//    Idea clave O(n) con HashSet
//    Mete todos los números en un HashSet<Integer> → búsquedas O(1).
//
//    Recorre cada número; sólo arrancas una exploración si es posible inicio de una secuencia (es decir, num‑1 no está en el set).
//
//    Mientras num+len esté en el set vas contando (len++).
//
//    Vas actualizando el máximo.
//
//    Así evitas ordenar (que costaría O(nlogn)) y mantienes O(n) tiempo / O(n) espacio.
    public static void longestSequence(Set<Integer> set){
        Integer max = Integer.MIN_VALUE;
        int longest=0;
        for(Integer i: set){
            if(i>max){
                max = i;
            }
        }

        int tempLongest=0;
        for(int i=0; i<=max; i++){
            if(set.contains(i)){
                tempLongest++;
                if(tempLongest>longest){
                    longest=tempLongest;
                }
            }else{
                tempLongest=0;
            }
        }
        System.out.println(longest);
    }

    //reverse
    public static void reverse(String word ){
        int length = word.length();
        char[] array = word.toCharArray();

        for(int i=0; i<length/2; i++){
            char temp = array[i];
            array[i] = array[length-1-i];
            array[length-1-i] = temp;
        }

        System.out.println(new String(array));
    }

    public static boolean palindrome(String word){

        int length = word.length();

        for(int i=0; i< length/2; i++){
            if(word.charAt(i)!=word.charAt(length-1-i)){
                return false;
            }
        }
        return true;
    }

    public static void secondLargest(List<Integer> list){
        list.stream().sorted(Comparator.reverseOrder()).skip(1).limit(1).forEach(System.out::println);
    }

    public static int countVowels(String word){
        Set<Character> set = new HashSet<>(){{add('a'); add('e'); add('i'); add('o'); add('u');}};
        int count =0;
        word = word.toLowerCase();
        for(int i=0; i<word.length(); i++){
            if(set.contains(word.charAt(i))){
                count++;
            }
        }
        return count;
    }

    //boxed to convert from int[] to Integer
    public static void removeDuplicates(int[] array){

        Set<Integer> set =Arrays.stream(array).boxed().collect(Collectors.toCollection(LinkedHashSet::new));
        System.out.println(set);
    }

    public static boolean anagram(String a, String b){
        char[] aArray = a.toCharArray();
        char[] bArray = b.toCharArray();

        Arrays.sort(aArray);
        Arrays.sort(bArray);

        return Arrays.equals(aArray, bArray);
    }

    public static void countOcurrences(String word){
        char[] array = word.toCharArray();

        Map<Character, Long> collect = word.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(collect);
    }

    private static boolean arrayHasDuplicates(int[] ints) {
        Set<Integer> set = new HashSet<>();
       for(int i : ints){
           if(!set.add(i)){
               return true;
           }
       }
       return false;
    }

    public static void main(String[] args) {
        groupAnagram();line();
        moreFrequentWord(Stream.of("apple", "banana", "apple", "orange", "banana", "apple"), 2);line();
        longestSequence(Set.of(100, 4, 200, 1, 3, 2,5,6,7));line();
        reverse("hola mundo");
        System.out.println(palindrome("fereef"));
        secondLargest(List.of(7,9,8,4,2,5,10));
        System.out.println(countVowels("FernAndoaaaauu"));
        removeDuplicates(new int[]{1,2,2,3,4,4,5});
        System.out.println(anagram("fernnn","nnafer"));
        countOcurrences("programa");
        System.out.println(arrayHasDuplicates(new int[]{1,2,3,4}));

    }



}
