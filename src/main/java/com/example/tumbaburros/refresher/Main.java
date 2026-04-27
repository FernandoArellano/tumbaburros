package com.example.tumbaburros.refresher;


import com.example.tumbaburros.java8.Student;
import com.example.tumbaburros.streams.DataSource;
import com.example.tumbaburros.streams.Pedido;
import com.example.tumbaburros.streams.Producto;
import com.example.tumbaburros.streams.Usuario;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void toUse(){

    }

    public static void main(String[] args) {
        System.out.println(generateParenthesis(3));
        System.out.println(intToRoman(4941));
        System.out.println(convert("AB",1));
        printPermutations("abc");
        System.out.println(groupAnagrams(List.of("eat", "tea", "tan","ate","nat","bat")));
        System.out.println(longestConsecutive(new int[]{1,2,6,7,8}));
        System.out.println(fourSum(new int[]{1,0,-1,0,-2,2}, 0));
        System.out.println(threeSum(new int[]{-1,0,1,2,-1,-4}));
        System.out.println(reverse(-123));
        longestPalindrome("babad");
        stringPatternMatching("amazingamazing", "010");
        arrayManipulation(new int[]{4, 0, 1, -2, 3});
        System.out.println(search(new int[]{-1,0,3,5,9,12}, 2));
        insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}}, new int[]{4,8});
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4}));
        System.out.println(reverseStr("abcdefg",2));
        int[]result = twoSum(new int[]{2,7,11,15}, 9);
        System.out.println(result[0] + " " + result[1]);
        System.out.println(trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        runningSum(new int[]{1,2,3,4});
        System.out.println(numIslands(new char[][]{
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'}
        }));
        System.out.println(numEnclaves(new int[][]{{0,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}}));
        spiralGridTraversal(5,6,1,4);

        int [][] array = new int[][]{
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        rotate90(array);
        largestSegmentAfterRemoval(new int[]{3,5,1,2,4});
        System.out.println(segmentsAfterRemoval(new int[]{3,5,1,2,4}));
        System.out.println(numberOfSegments(new int[]{2,4,3,1}));
        System.out.println(findHouses(new int[]{10,2,5,4,3,7,9,1}));
        System.out.println(findMaxConnectedHouses(new int[]{1,2,4,5,3,6}));
        List<Integer> hubs1 = Arrays.asList(1, 3, 3, 2);
        List<Integer> time = Arrays.asList(3, 2, 1);

        System.out.println(minTime(hubs1, time)); // ✅ 4

        List<Integer> hubs2 = Arrays.asList(2, 3, 3, 1);
        System.out.println("Amazon min Time: " +minTime(hubs2, time));


        System.out.println(findMissingSum(new int[]{1,3,4,5,6,7,8,9},9));

        System.out.println(getMinSwaps(new int[] {0, 1, 0, 1, 0}));

        List<Student> students = Util.getStudents();

        //Collectors.toCollection(TreeSet::new)
        Set<Integer> grades= students.stream().map(s->s.getGrade()).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(grades);


        //collectors comparingandthen   Encontrar el mejor grade por escuela
        Map<String, Student> collect2 = students.stream().collect(Collectors.groupingBy(
                Student::getSchool,
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparing(Student::getGrade)), optional -> optional.orElse(null)
                )
        ));
        System.out.println(collect2);


        //collectors.filtering get school and list of students with grade 5 or above
        Map<String, List<Student>> collect1 = students.stream().collect(Collectors.groupingBy(
                Student::getSchool,
                Collectors.filtering(s -> s.getGrade() >= 5, Collectors.toList())
        ));
        System.out.println(collect1);


        //collectors partitioning by from a list return alumns with grade above 5 and below or equal to 5

        Map<Boolean, List<Student>> collect = students.stream().collect(Collectors.partitioningBy(s -> s.getGrade() >= 6));
        System.out.println(collect);


        //from a list of strings, join all words separated by space with , considering that each element in the list might have more than 1 word
        //arrays.stream
        List<String> sentences = Arrays.asList("hello world", "java is awesome");
        System.out.println(sentences);
        String collect3 = sentences.stream().flatMap(s -> Arrays.stream(s.split(" "))).collect(Collectors.joining(","));
        System.out.println(collect3);
        Stream<String> stream = Arrays.stream(new String[]{"fer", "adf"});
        Set<String> collect4 = stream.collect(Collectors.toSet());

        //get 10 random numbers from 0 to 999 and add 10 to each one of them, then print the list
        List<Integer> integers = Stream.generate(() -> new Random().nextInt(999)).limit(10).map(i -> i + 10).toList();
        System.out.println(integers);


        //from a list of emails get a map of the dominion (.com) as kwy and the elements which have that dominion as value
        List<String> correos = List.of("foo@bar.com", "baz@bar.com", "foo@baz.com");
        Map<String, List<String>> collect5 = correos.stream().collect(Collectors.groupingBy(
                s -> s.split("@")[0], Collectors.toList()
        ));
        System.out.println(collect5);


        //get the longest word from a list of strings
        List<String> palabras = List.of("stream", "java", "colecciones", "api");
        String longest = palabras.stream().max(Comparator.comparing(String::length)).orElse("error");
        System.out.println(longest);


        //from a list of names, group them into initial letter as key and the and the longest word with that initial as value
        List<String> nombres3 = List.of("Ana", "Alberto", "Bruno", "Benito");
        Map<Character, String> collect6 = nombres3.stream().collect(Collectors.groupingBy(
                s -> s.charAt(0),
                Collectors.collectingAndThen(
                        Collectors.maxBy(Comparator.comparing(s -> s.length())), optional -> optional.get()
                )
        ));
        System.out.println(collect6);


        //convert list of numbers into sorted set using treeset
        List<Integer> nums = List.of(3, 1, 4, 2, 3);
        TreeSet<Integer> collect7 = nums.stream().collect(Collectors.toCollection(TreeSet::new));
        System.out.println(collect7);


        //get sum of cuadratic values from list (2*2)+(3*3...
        List<Integer> nums2 = List.of(2, 3, 4);
        Integer i3 = nums2.stream().map(i -> i * 2).reduce((i1, i2) -> i1 + i2).orElse(-1);
        System.out.println(i3);


        List<Usuario> usuarios = DataSource.getUsuarios();
        System.out.println("getTodosLosPedidos: "+getTodosLosPedidos(usuarios));
        System.out.println("getTotalGastadoPorUsuario: "+getTotalGastadoPorUsuario(usuarios, "Carlos"));
        System.out.println("getProductosTecnologia: "+getProductosTecnologia(usuarios));
        System.out.println("getPromedioEdad: "+getPromedioEdad(usuarios));
        System.out.println("contarUsuariosConMasDeUnPedido: "+contarUsuariosConMasDeUnPedido(usuarios));
        System.out.println("getProductoMasBarato: " +getProductoMasBarato(usuarios));
        System.out.println("getTotalPorUsuario: "+ getTotalPorUsuario(usuarios));
        System.out.println("getUsuariosConComprasCaras: "+getUsuariosConComprasCaras(usuarios));
        System.out.println("getPedidoMasAntiguo: " +getPedidoMasAntiguo(usuarios));
        System.out.println("contarProductosTotales: " +contarProductosTotales(usuarios));
        System.out.println("getPromedioPrecioPorCategoria: " +getPromedioPrecioPorCategoria(usuarios));
        System.out.println("getGastoTotalSistema: "+getGastoTotalSistema(usuarios));
        System.out.println("todosTienenPedidos: " +todosTienenPedidos(usuarios));
        System.out.println("getUsuarioConMasGasto: " +getUsuarioConMasGasto(usuarios));
        System.out.println("mergeAlternately: " + mergeAlternately("Fernando","Arellano"));
        System.out.println("kids with candies"+kidsWithCandies(new int[]{2,3,5,1,3}, 3));
        System.out.println("reverse vowels"+reverseVowels("hello"));
        System.out.println("reverse words"+reverseWords("a good   example"));
        System.out.println("increasing triplet"+increasingTriplet(new int[]{2,1,5,0,4,6}));
        System.out.println("compress"+ compress("aabbbccccccddbbddd".toCharArray()));
        moveZeroes(new int[]{0,1,0,3,12});
        System.out.println("is anagram:" + isAnagram("aabbbaa", "bbbaaaa"));

        lengetizar("2[bd]3[jk]");
        lengetizar("d2[bd]z3a[jk]e");
        lengetizar("abcde");
    }


    // 4. Listar todos los pedidos realizados (sin duplicados).
    public static List<Pedido> getTodosLosPedidos(List<Usuario> usuarios) {

        return usuarios.stream().flatMap(u->u.getPedidos().stream()).distinct().toList();
    }

    // 5. Obtener la suma total gastada por un usuario específico (por nombre).
    public static double getTotalGastadoPorUsuario(List<Usuario> usuarios, String nombre) {
        return usuarios.stream().filter(u->u.getNombre().equals(nombre))
                .flatMap(u->u.getPedidos().stream())
                .flatMap(p->p.getProductos().stream())
                .mapToDouble(Producto::getPrecio).sum();

    }

    // 6. Listar todos los productos comprados de la categoría "Tecnología".
    public static List<Producto> getProductosTecnologia(List<Usuario> usuarios) {
        return usuarios.stream()
                .flatMap(u->u.getPedidos().stream())
                .flatMap(p->p.getProductos().stream())
                .filter(p->p.getCategoria().equals("Tecnologia"))
                .toList();
    }

    // 7. Obtener el promedio de edad de los usuarios.
    public static double getPromedioEdad(List<Usuario> usuarios) {

        return usuarios.stream().mapToDouble(Usuario::getEdad).average().getAsDouble();
    }


    // 10. Contar cuántos usuarios tienen más de 1 pedido.
    public static long contarUsuariosConMasDeUnPedido(List<Usuario> usuarios) {
        return usuarios.stream()
                .filter(u->u.getPedidos().stream().count()>1)
                .count();
    }

    // 11. Obtener el producto más barato comprado.
    public static Optional<Producto> getProductoMasBarato(List<Usuario> usuarios) {
        return usuarios.stream()
                .flatMap(u->u.getPedidos().stream())
                .flatMap(p->p.getProductos().stream())
                .min(Comparator.comparing(Producto::getPrecio));
    }

    // 12. Crear un mapa usuario->total gastado.
    public static Map<String, Double> getTotalPorUsuario(List<Usuario> usuarios) {

        return usuarios.stream().collect(
                Collectors.toMap(Usuario::getNombre,
                        u->u.getPedidos().stream().flatMap(p->p.getProductos().stream())
                                .mapToDouble(Producto::getPrecio).sum()
                )
        );

    }

    // 13. Obtener la lista de nombres de usuarios que han comprado productos de más de 500.
    public static List<String> getUsuariosConComprasCaras(List<Usuario> usuarios) {

        return usuarios.stream()
                .filter(u->u.getPedidos().stream()
                        .flatMap(p->p.getProductos().stream()).anyMatch(p->p.getPrecio()>500))
                .map(u->u.getNombre()).toList();
    }



    // 16. Calcular el pedido más antiguo realizado.
    public static Optional<Pedido> getPedidoMasAntiguo(List<Usuario> usuarios) {

        return usuarios.stream()
                .flatMap(u->u.getPedidos().stream())
                .min(Comparator.comparing(Pedido::getFecha));
    }


    // 19. Contar cuántos productos ha comprado en total el sistema.
    public static long contarProductosTotales(List<Usuario> usuarios) {

        return usuarios.stream()
                .flatMap(u->u.getPedidos().stream())
                .flatMap(p->p.getProductos().stream())
                .count();
    }


    // 21. Obtener el promedio de precio de productos por categoría.
    public static Map<String, Double> getPromedioPrecioPorCategoria(List<Usuario> usuarios) {
        return usuarios.stream()
                .flatMap(u->u.getPedidos().stream())
                .flatMap(p->p.getProductos().stream())
                .collect(Collectors.groupingBy(
                        Producto::getCategoria,
                        Collectors.averagingDouble(Producto::getPrecio)
                ));
    }

    // 25. Obtener el gasto total de todos los pedidos.
    public static double getGastoTotalSistema(List<Usuario> usuarios) {
        return usuarios.stream()
                .flatMap(u->u.getPedidos().stream())
                .flatMap(p->p.getProductos().stream())
                .mapToDouble(Producto::getPrecio)
                .sum();
    }

    // 27. Verificar si todos los usuarios tienen al menos un pedido.
    public static boolean todosTienenPedidos(List<Usuario> usuarios) {
        return usuarios.stream()
                .allMatch(u->u.getPedidos().size()>0);
    }

    // 28. Encontrar el usuario con más gasto total.
    public static Optional<Usuario> getUsuarioConMasGasto(List<Usuario> usuarios) {
        return usuarios.stream().max(Comparator.comparing(
                u->u.getPedidos().stream()
                        .flatMap(p->p.getProductos().stream())
                        .mapToDouble(Producto::getPrecio).sum()
        ));
    }


    /*
         You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1.
         If a string is longer than the other, append the additional letters onto the end of the merged string.
         Return the merged string.
     */
    public static String mergeAlternately(String word1, String word2) {

        int size = word1.length() + word2.length();
        int counter= 0;
        int position =0;
        StringBuilder sb = new StringBuilder();

        while(counter<size){
            if(position<word1.length()){
                sb.append(word1.charAt(position));
                counter++;
            }

            if(position<word2.length()){
                sb.append(word2.charAt(position));
                counter++;
            }

            position++;
        }

        return sb.toString();
    }

    /*
       There are n kids with candies. You are given an integer array candies, where each candies[i] represents the number of candies the
       ith kid has, and an integer extraCandies, denoting the number of extra candies that you have.
       Return a boolean array result of length n, where result[i] is true if, after giving the ith kid all the extraCandies,
       they will have the greatest number of candies among all the kids, or false otherwise.

       Note that multiple kids can have the greatest number of candies.

       find biggest and compare each kid + extra candies  to biggest

       ex: System.out.println(kidsWithCandies(new int[]{2,3,5,1,3}, 3));
    */
    public static List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new LinkedList<>();

        int max = IntStream.of(candies).boxed().max(Comparator.naturalOrder()).orElse(-1);
        for(int i: candies){
            if(i+extraCandies>=max){
                result.add(true);
            } else{
                result.add(false);
            }
        }

        return result;
    }

    /*
          Given a string s, reverse only all the vowels in the string and return it.
          The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

          stack vowels only and pop in reverse order when vowel found

          ex: System.out.println(reverseVowels("hello"));
       */
    public static String reverseVowels(String s) {

        Set<Character> set = Set.of('a','e','i','o','u');
        Stack<Character> stack = new Stack<>();
        StringBuilder sb= new StringBuilder();

        for(int i=0; i<s.length(); i++){
            if(set.contains(s.charAt(i))){
                stack.push(s.charAt(i));
            }
        }

        for(int i=0; i<s.length();i++){
            if(set.contains(s.charAt(i))){
                sb.append(stack.pop());
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    /*
      Given an input string s, reverse the order of the words.
      A word is defined as a sequence of non-space characters. The words in s will be separated by at least one space.
      Return a string of the words in reverse order concatenated by a single space.
      Note that s may contain leading or trailing spaces or multiple spaces between two words.
      The returned string should only have a single space separating the words. Do not include any extra spaces.

      trim removes spaces at start and end
      replaceAll(" +", " ")  will replace many spaces together for only 1 space

      ex: System.out.println(reverseWords("a good   example"));
   */
    public static String reverseWords(String s) {

        s= s.replaceAll(" +", " ");
        String[] array = s.split(" ");
        Stack<String>stack = new Stack<>();
        for(String ss: array){
            stack.push(ss);
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()){
            sb.append(stack.pop());
            if(!stack.isEmpty()){
                sb.append(" ");
            }
        }
        return sb.toString();
    }

    /*
Given an integer array nums, return true if there exists a triple of indices (i, j, k)
such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.

ex:
Input: nums = [2,1,5,0,4,6]
Output: true
Explanation: The triplet (3, 4, 5) is valid because nums[3] == 0 < nums[4] == 4 < nums[5] == 6.

System.out.println(increasingTriplet(new int[]{2,1,5,0,4,6}));
*/
    public static boolean increasingTriplet(int[] nums) {

        int medium = Integer.MAX_VALUE;
        int small = Integer.MAX_VALUE;

        for(int big:nums){
            if(big<small){
                small= big;
            } else if(big<medium){
                medium = big;
            } else{
                return true;
            }
        }

        return false;
    }

    /*
           Given an array of characters chars, compress it using the following algorithm:
           Begin with an empty string s. For each group of consecutive repeating characters in chars:
           If the group's length is 1, append the character to s.
           Otherwise, append the character followed by the group's length.
           The compressed string s should not be returned separately, but instead, be stored in the input character array chars.
           Note that group lengths that are 10 or longer will be split into multiple characters in chars.
           After you are done modifying the input array, return the new length of the array.
           You must write an algorithm that uses only constant extra space.

           Input: chars = ["a","a","b","b","c","c","c"]
           Output: Return 6, and the first 6 characters of the input array should be: ["a","2","b","2","c","3"]
           Explanation: The groups are "aa", "bb", and "ccc". This compresses to "a2b2c3".
    */
    public static int compress(char[] chars) {

        if(chars.length<=1) return chars.length;

        char previous = chars[0];
        int counter=1;
        StringBuilder sb = new StringBuilder();

        for(int i =1; i<chars.length; i++){
            if(chars[i]== previous){
                counter++;
            } else {
                sb.append(previous).append(counter);
                counter=1;
                previous = chars[i];
                if(i+1 == chars.length){
                    sb.append(previous).append(counter);
                }
            }
        }

        if(counter>1){
            sb.append(previous).append(counter);
        }

        System.out.println(sb);
        return sb.length();
    }

    /*
   Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
   Note that you must do this in-place without making a copy of the array.

   ex: moveZeroes(new int[]{0,1,0,3,12});
*/
    public static void moveZeroes(int[] nums) {

        int zeros = 0;
        int index=0;

        for(int i=0; i<nums.length;i++){
            if(nums[i]==0){
                zeros++;
            } else {
                nums[index] = nums[i];
                index++;
            }
        }
        for(int i=0; i<zeros;i++){
            nums[index]=0;
            index++;
        }
    }


    //check anagram (if 2 words have the same characters)
    public static boolean isAnagram(String a, String b) {
        char[] a1 = a.toCharArray();
        char[] b1 = b.toCharArray();

        Arrays.sort(a1);
        Arrays.sort(b1);
        return Arrays.equals(a1,b1);
    }

    //Implement Stack Using Array
    static class ArrayStack {
        private int[] arr = new int[10];
        int top=-1;

        public void push(int x){
            arr[++top] = x;
        }

        public int pop(){
            return arr[top--];
        }

    }

    //Reverse Linked List
    class Node {
        int val;
        Node next;
    }

    // reverse nodes
    public static Node reverse(Node head) {


        return null;
    }

    //create a cache class
    class LRUCache extends LinkedHashMap<Integer, Integer> {

        int capacity;

        LRUCache(int capacity){
            super(capacity, 0.75f, true);
            this.capacity=capacity;
        }

        public boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
            return this.size()>capacity;
        }


    }
    //balanced {[()]}
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (ch == ')' && top != '(') {
                    return false;
                }
                if (ch == ']' && top != '[') {
                    return false;
                }
                if (ch == '}' && top != '{') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    //merge 2 sorted arrays adding them to still be sorted without sorting after
    public static int[] merge(int[] a, int[] b) {

        int[] result = new int[a.length + b.length];
        int i=0,j=0,k=0;

        while(i<a.length && j<b.length){

            if(a[i] < b[j])
                result[k++] = a[i++];
            else
                result[k++] = b[j++];
        }

        while(i<a.length) result[k++] = a[i++];
        while(j<b.length) result[k++] = b[j++];

        return result;
    }

    //detect cycle (circular list with linkedlist
    public static boolean hasCycle(Node head) {

        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null){

            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast)
                return true;
        }

        return false;
    }

    //find intersection, elements in a which also exist in b
    public static Set<Integer> intersection(int[] a, int[] b) {

        Set<Integer> set = Arrays.stream(a)
                .boxed()
                .collect(Collectors.toSet());

        return Arrays.stream(b)
                .boxed()
                .filter(set::contains)
                .collect(Collectors.toSet());
    }

    //singleton double validation if its null
    static class Singleton {

        private static volatile Singleton instance;

        private Singleton(){}

        public static Singleton getInstance(){

            if(instance == null){
                synchronized(Singleton.class){
                    if(instance == null){
                        instance = new Singleton();
                    }
                }
            }

            return instance;
        }
    }

    //sort from a map to a map with map value
    public static Map<String,Integer> sortByValue(Map<String,Integer> map){

        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a,b)->a,
                        LinkedHashMap::new
                ));
    }

    //mapping inside a grouping does the map
//    Map<String,List<String>> result =
//            people.stream()
//                    .collect(Collectors.groupingBy(
//                            Person::city,
//                            Collectors.mapping(Person::name, Collectors.toList())
//                    ));


    //multilevelgrouping
//    Map<String, Map<Integer, List<Person>>> result =
//            people.stream()
//                    .collect(Collectors.groupingBy(
//                            Person::city,
//                            Collectors.groupingBy(Person::age)
//                    ));

    //summirizing use
//    DoubleSummaryStatistics stats =
//            people.stream()
//                    .collect(Collectors.summarizingDouble(Person::salary));

    class SimpleThreadPool {

        private final BlockingQueue<Runnable> queue;
        private final List<Worker> workers;

        public SimpleThreadPool(int threads) {

            queue = new LinkedBlockingQueue<>();
            workers = new ArrayList<>();

            for(int i=0;i<threads;i++){
                Worker worker = new Worker();
                workers.add(worker);
                worker.start();
            }
        }

        public void submit(Runnable task){
            queue.offer(task);
        }

        class Worker extends Thread {

            public void run(){

                while(true){

                    try {
                        Runnable task = queue.take();
                        task.run();
                    }
                    catch(Exception e){}
                }
            }
        }
    }

    //deep copy of list of objects, clone list, but also the objects inside it
    public List<Person> deepCopy(List<Person> list){

        return list.stream()
                .map(p -> new Person(p.getName(), p.getAge()))
                .toList();
    }

    class Person{
        String name;
        int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    //Implement Rate Limiter (token bucket to see if allow the request based in if
    //there are tokens available
    class RateLimiter {

        private final int capacity;
        private int tokens;
        private long lastRefill;

        public RateLimiter(int capacity){
            this.capacity = capacity;
            this.tokens = capacity;
            this.lastRefill = System.currentTimeMillis();
        }

        synchronized boolean allowRequest(){

            refill();

            if(tokens > 0){
                tokens--;
                return true;
            }

            return false;
        }

        private void refill(){

            long now = System.currentTimeMillis();

            if(now - lastRefill > 1000){
                tokens = capacity;
                lastRefill = now;
            }
        }
    }

    //Find Longest Substring Without Repeating Characters
    //using slidingWindow
    public int longestUniqueSubstring(String s) {
        int left = 0, maxLength = 0;
        HashSet<Character> set = new HashSet<>();

        for (int right = 0; right < s.length(); right++) {
            // Contraer la ventana si hay un duplicado
            while (set.contains(s.charAt(right))) {
                set.remove(s.charAt(left));
                left++;
            }
            // Expandir la ventana
            set.add(s.charAt(right));
            maxLength = Math.max(maxLength, right - left + 1);
        }
        return maxLength;
    }

    public void detectDeadlock(){
        //detect Deadlock with ManagementFactory
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();

        long[] ids = bean.findDeadlockedThreads();

        if(ids != null){
            ThreadInfo[] infos = bean.getThreadInfo(ids);

            for(ThreadInfo info : infos){
                System.out.println(info.getThreadName());
            }
        }
    }



    //copy 2 arrays into a merged array
    // find median value after merged
    public static double median(int[] a, int[] b){

        int[] merged = new int[a.length + b.length];

        System.arraycopy(a,0,merged,0,a.length);
        System.arraycopy(b,0,merged,a.length,b.length);

        Arrays.sort(merged);

        int mid = merged.length/2;

        if(merged.length%2==0)
            return (merged[mid-1]+merged[mid])/2.0;

        return merged[mid];
    }

    //word count of a file with parallel stream
    public void wordCountingFromFile() throws IOException {
        Map<String,Long> count = Files.lines(Path.of("file.txt"))
                .parallel()
                .flatMap(line -> Arrays.stream(line.split(" ")))
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ));
    }

    //ForkJoinPool

    //build Map implementation
    public static <T,R> List<R> map(
            List<T> list,
            Function<T,R> mapper){

        List<R> result = new ArrayList<>();

        for(T element : list){
            result.add(mapper.apply(element));
        }

        return result;
    }

    //prefix sum to give the sum of an array from L (left) to R (right)
    public static int[] buildPrefixSum(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n];

        if (n == 0) return prefixSum;

        prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i]; // Accumulate the sum
        }

        return prefixSum;
    }

    // Query sum from index 1 to 3 (elements 2, 3, 4)
    // Function to calculate the sum of a subarray from index L to R
//    int[] arr = {1, 2, 3, 4, 5};
//    // Build the prefix sum array
//    int[] prefix = buildPrefixSum(arr);
//    // Output: [1, 3, 6, 10, 15]


    public static int rangeSum(int[] prefixSum, int L, int R) {
        if (L == 0) {
            return prefixSum[R];
        } else {
            return prefixSum[R] - prefixSum[L - 1]; // Constant time subtraction
        }
    }


    //backtracking
    //all combinacions from letters in a word
    /**
     * @param pasoActual Lo que hemos construido hasta ahora
     * @param restantes  Lo que nos falta por usar
     * @param lista      Donde guardamos los resultados finales
     */
    private static void permutar(String pasoActual, String restantes, List<String> lista) {
        // CASO BASE: Si ya no quedan letras, hemos terminado una combinación
        if (restantes.length() == 0) {
            lista.add(pasoActual);
            return;
        }

        for (int i = 0; i < restantes.length(); i++) {
            // 1. ELEGIR: Tomamos una letra
            char letra = restantes.charAt(i);

            // 2. EXPLORAR: Llamada recursiva con el resto de las letras
            // Quitamos la letra elegida de 'restantes'
            String nuevoResto = restantes.substring(0, i) + restantes.substring(i + 1);

            permutar(pasoActual + letra, nuevoResto, lista);

            // 3. BACKTRACKING: En este caso es implícito.
            // Al volver de la recursión, 'pasoActual' y 'restantes'
            // conservan sus valores originales del ciclo anterior.
        }
    }

//    lengetizar("2[bd]3[jk]");
//    lengetizar("d2[bd]z3a[jk]e");
//    lengetizar("abcde");

    public static void lengetizar(String s){

        char[] array = s.toCharArray();
        StringBuilder result = new StringBuilder();
        StringBuilder sb = new StringBuilder();  //
        int multiplier = 0;
        boolean inside=false;

        for(char c : array){
            if(c =='['){
                multiplier = Integer.parseInt(sb.toString());
                sb = new StringBuilder();
                inside = true;
            } else if(c== ']'){
                result.append(sb.toString().repeat(multiplier));
                sb=new StringBuilder();
                inside=false;
            } else if(Character.isLetter(c) && !inside){
                result.append(c);
            }else {
                sb.append(c);
            }
        }
        System.out.println(result);
    }

    //Find kth largest element
    public int findKthLargest(List<Integer> list, int k){
        return list.stream().sorted(Comparator.reverseOrder()).skip(k-1).findFirst().get();
    }


    public boolean basicRateLimiter(String user){
        Map<String, Long> map = new HashMap<>();

        if(!map.containsKey(user) || System.currentTimeMillis() - map.get(user) > 1000){
            map.put(user, System.currentTimeMillis());
            return true;
        }

        return false;
    }

    public void topKFrequentElement(List<Integer> list, int k){

        Map<Integer, Long> map = list.stream().collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));

        long value = map.values().stream().sorted(Comparator.reverseOrder()).skip(k-1).findFirst().get();

        System.out.println(value);

    }

    //find missing number from an array
    //suma de gauss para no usar tanta memoria adicional
    public static int findMissingSum(int[] nums, int n) {
        int expectedSum = n * (n + 1) / 2;
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num;
        }
        return expectedSum - actualSum;
    }

    public static String removeLeadingZeros(String number){

        IntStream.range(1,5).forEach(i-> {
            System.out.println(i + " - " + i*i);
        });

        int zeroCount = 0;
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<number.length();i++){
            if(number.charAt(i)=='0'){
                zeroCount++;
            }
            else {
                sb.append(number.charAt(i));
            }
        }
        for(int i=0;i<zeroCount;i++){
            sb.append(0);
        }
        return sb.toString();
    }

    //swift 0s or 1s and get min swaps (they dont interchange places, need yo be moved
    // 1 position at a time ant that counts as a swap for the counter
    public static long getMinSwaps(int[] arr) {
        long swapsToLeft = 0;
        long swapsToRight = 0;
        int countZeros = 0;
        int countOnes = 0;

        for (int num : arr) {
            if (num == 0) {
                // Para llevar ceros a la izquierda, saltamos sobre los 1s acumulados
                swapsToLeft += countOnes;
                countZeros++;
            } else {
                // Para llevar unos a la izquierda, saltamos sobre los 0s acumulados
                swapsToRight += countZeros;
                countOnes++;
            }
        }

        //{0, 1, 0, 1, 0} result 3
        // el segundo 0 se mueve 1 a la izq (1), el tercer cero se mueve 2 (2) a la izq
        //dando los 3, con contar cuantos del inverso hubo antes de encontrar el 0 queda

        // Retornamos el menor de ambos escenarios
        return Math.min(swapsToLeft, swapsToRight);


    }


    /*
        Amazon receives a list of prority delivery requests, where packages must be picked up or
         delivered to specific hubs in a given sequence, represented by the array requestedHubs of size n.
          Starting from Hub 1, your task is to calculate the minimum total travel time required for the
           drone to fullfill all delivery requests. Note use 1 based indexing. example:
           transition time= 3,2,1
            requestedHubs= 1,3,3,2
            The drone begin its journey at Hub 1. The first hub to visit is Hub 1 itself,
             so it takes 0 seconds to complete this step.
             To move from hub 1 to hub 3, the drone has 2 possible
              routes: clockwise: 1,2,3 which takes transitionTime[1]+transitionTime[2]= 3+2= 5 seconds
              Counterclockwise: 1,3 which takes transition time[1] = 3 seconds The shorter route is the
              counterclockwise path, so the drone reaches hub 3 in 3 seconds.
              The drone is already at hub 3,
              so it takes 0 seconds to complete this step.
              To move from hub 3 to hub 2 the drone has
              2 possible routes: clockwise: 3,2 which takes transitiontimes[3] = 1 second.
              counterclockwise 3,1,2 which takes transitiontime[3]+transitionTime[1] = 4 seconds the shorter
              route is the clockwise path, so the drone reaches hub 2 in 1 second Hence the total minimum possible
              time to visit all the required servers is 4 seconds. Complete the function to get this minimum possible time.
               ex: requestedHubs= [2,3,3,1] transitiontime = [3,2,1] output expected 6
     */
    public static int minTime(List<Integer> requestedHubs, List<Integer> transitionTime) {
        int n = transitionTime.size();

        // Build prefix sum for clockwise distances
        int[] prefix = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + transitionTime.get(i);
        }

        int totalSum = prefix[n];

        // Function to get clockwise distance
        java.util.function.BiFunction<Integer, Integer, Integer> clockwise = (a, b) -> {
            if (a <= b) {
                return prefix[b - 1] - prefix[a - 1];
            } else {
                return totalSum - (prefix[a - 1] - prefix[b - 1]);
            }
        };

        int totalTime = 0;
        int current = 1; // starts at hub 1

        for (int next : requestedHubs) {
            int cw = clockwise.apply(current, next);
            int ccw = totalSum - cw;

            totalTime += Math.min(cw, ccw);
            current = next;
        }

        return totalTime;
    }

    //merge 2 maps, keeping old if collision
    public static void merge2Maps(Map<Integer, Integer>m1,Map<Integer, Integer> m2){
        m1.forEach((k,v) -> m2.merge(k,v,(oldv, newv) ->oldv));
    }

    //Find common elements in 3 arrays
    public static void retainAll(List<Integer> l1, List<Integer> l2,List<Integer> l3){
        Set<Integer> s1 = new HashSet<>(l1);
        s1.retainAll(l2);
        s1.retainAll(l3);
        System.out.println(s1);
    }

    //find houses which are together on each step ( number of houses connected after adding that house considering this house as root only
    //not what is the longest number of connected houses in total
    public static List<Integer> findHouses(int[] toBuild){

        if(toBuild.length==0) return Collections.emptyList();

        List<Integer> list = new LinkedList<>();
        Map<Integer, Integer> visited = new HashMap<>();

        for(int x:toBuild){
            //2,4,3,7
            if(visited.containsKey(x)){
                continue;
            }

            int left = visited.getOrDefault(x-1,0); //0
            int right = visited.getOrDefault(x+1,0); //0
            int total = left + right + 1; //1

            list.add(total); //1,1,3,1

            visited.put(x, total);  // 2,3 4,3, 3,3,7,1
            visited.put(x-left, total);
            visited.put(x+right, total);

        }
        return list;
    }

    //find houses which are together on each step ( number of houses connected after adding that house considering this house as root only
    //not what is the longest number of connected houses in total
    public static List<Integer> findMaxConnectedHouses(int[] toBuild){

        List<Integer> result = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int max =0;

        for(int x: toBuild){
            if(map.containsKey(x)){
                result.add(max);
                continue;
            }

            int left = map.getOrDefault(x-1,0);
            int right = map.getOrDefault(x+1,0);
            int total = left+right+1;
            result.add(Math.max(total, max));
            if(total>max){
                max=total;
            }

            map.put(x, total);
            map.put(x-left, total);
            map.put(x+right, total);
        }

        return result;
    }

    //number of segments Return how many separate groups of houses exist after each build.
    public static List<Integer> numberOfSegments(int[] toBuild){
        int segments=0;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new LinkedList<>();

        for(int x: toBuild){
            if(map.containsKey(x)){
                continue;
            }

            map.put(x, 1);
            if(map.getOrDefault(x-1,0)==0 && map.getOrDefault(x+1,0)==0){
                segments++;
            }else if(map.getOrDefault(x-1,0)!=0 && map.getOrDefault(x+1,0)!=0) {
                segments--;
            }

            list.add(segments);
        }

        return list;
    }

    //remove houses
    public static List<Integer> segmentsAfterRemoval(int[] toRemove){
        List<Integer> list = new LinkedList<>();
        Map<Integer, Integer> existingHouses= new HashMap<>();
        int segments=1;

        for(int x:toRemove){
            existingHouses.put(x,x);
        }

        for(int x: toRemove){
            if(!existingHouses.containsKey(x)){
                continue;
            }

            existingHouses.remove(x);
            if(existingHouses.containsKey(x-1) && existingHouses.containsKey(x+1)){
                segments++;
            } else if(existingHouses.getOrDefault(x+1,0)==0
                    && (existingHouses.getOrDefault(x-1,0)==0)){
                segments--;
            }

            list.add(segments);
        }
        return list;
    }

    //remove and keep looking segments is hard and inneficient, insted we will build
    //in reverse order which would be like removing in the original order
    public static void largestSegmentAfterRemoval(int[] toRemove){

        //3,5,1,2,4
        //reverse adding 4,2,1,5,3
        int max =0;
        Map<Integer, Integer> map = new HashMap<>();
        int n=toRemove.length;
        boolean[] active = new boolean[n+2];
        int[] result= new int[n];

        for(int i=n-1;i>=0;i--){
            result[i]= max;
            int x=toRemove[i];
            active[x] = true;

            int left = active[x-1] ? map.get(x-1):0;
            int right = active[x+1] ? map.get(x+1):0;
            int total = left+right+1;

            max = Math.max(max, total);

            map.put(x,total);
            map.put(x-left, total);
            map.put(x+right, total);
        }

        for(int i = 0; i<result.length; i++){
            System.out.print(result[i]+" ");
        }
        System.out.println();
    }

    //https://leetcode.com/problems/rotate-image/description/

    //You are given an n x n 2D matrix representing an image, rotate the image by 90 degrees (clockwise).

    //You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation
    //Input: matrix = [[1,2,3],[4,5,6],[7,8,9]]
    //Output:         [[7,4,1],[8,5,2],[9,6,3]]
    //Step 1: Transpose
    //Swap matrix[i][j] with matrix[j][i]
     //Step 2: Reverse each row
    public static void rotate90(int [][] m){

        for(int i=0; i<m.length; i++){
            for(int j=0;j<m.length;j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }


        for(int i=0; i<m.length; i++){
            for(int j=i;j<m.length;j++){
                int temp = m[j][i];
                m[j][i]=m[i][j];
                m[i][j]= temp;
            }
        }

        for(int i=0; i<m.length; i++){
            for(int j=0;j<m.length;j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }

        for(int i=0; i<m.length;i++){
            for(int j=0; j<m.length/2;j++){
                int temp=m[i][j];
                m[i][j] = m[i][m.length-1-j];
                m[i][m.length-1-j]= temp;
            }
        }

        for(int i=0; i<m.length; i++){
            for(int j=0;j<m.length;j++){
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }


    }

    /*
        https://leetcode.com/problems/spiral-matrix-iii/description/

        You start at the cell (rStart, cStart) of an rows x cols grid facing east. The northwest corner is at the first row and column in the grid,
        and the southeast corner is at the last row and column.
        You will walk in a clockwise spiral shape to visit every position in this grid. Whenever you move outside the grid's boundary,
        we continue our walk outside the grid (but may return to the grid boundary later.). Eventually, we reach all rows * cols spaces of the grid.
        Return an array of coordinates representing the positions of the grid in the order you visited them.
        Input: rows = 1, cols = 4, rStart = 0, cStart = 0
        Output: [[0,0],[0,1],[0,2],[0,3]]
     */
    //You are not “spiraling inside the grid”.
    //
    //👉 You are walking in an infinite spiral path, and only recording positions when they fall inside the grid
    //→ (east)
    //↓ (south)
    //← (west)
    //↑ (north)
    //(repeat...)
    //But the step size grows like this: (each 2 directions grow 1
    //
    //1 step →
    //1 step ↓
    //2 steps ←
    //2 steps ↑
    //3 steps →
    //3 steps ↓
    //4 steps ←
    //4 steps ↑
    //the size of the grid is m*n, so the total of visited valid coordinates is m*n
    public static void spiralGridTraversal(int rows, int cols, int rStart, int cStart){
        int directions[][]= {
                {0,1},   //dirs 1
                {1,0},    //dirs 2
                {0,-1},  //dirs 3
                {-1,0}}; //dirs 4
        int count=0;
        int totalGrids = rows*cols;
        int[][] coordinates = new int[totalGrids][2];
        int steps=1;
        int dir=0;
        int r=rStart;
        int c=cStart;

        coordinates[count++] = new int[]{r,c};
        while(count<totalGrids){
            for(int i=0; i<2; i++){  //each 2 movements steps increase
                for(int j=0; j<steps;j++){
                    r += directions[dir][0];   //increase row?
                    c += directions[dir][1];   //increase column? only 1 will increase per direction
                    if(r<rows && c<cols &&r>=0 && c>=0){
                        coordinates[count++] = new int[]{r,c};
                    }
                }
                dir = (dir+1) %4;
            }
            steps++;
        }

        for(int i=0; i<rows*cols; i++){
            for(int j=0; j<2;j++){
                System.out.print(coordinates[i][j] + " ");
            }
            System.out.println();
        }
    }

    /*
        https://leetcode.com/problems/number-of-enclaves/description/

        You are given an m x n binary matrix grid, where 0 represents a sea cell and 1 represents a land cell.
        A move consists of walking from one land cell to another adjacent (4-directionally) land cell or walking off the boundary of the grid.
        Return the number of land cells in grid for which we cannot walk off the boundary of the grid in any number of moves.
        Input: grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
        Output: 3
        Explanation: There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.

        instead of checking each land cell individually (which is expensive), we flip the perspective:
        👉 Find all land cells that CAN reach the boundary, and remove them.
        👉 The remaining land cells are the answer.
        Start from boundary land cells traverse throug edges only (start and end position of row and column)
        if there is a 1 dfs all the directions and update them to be 0
        count the remaining 1s
     */
    public static int numEnclaves(int[][] grid){
        int count=0;
        int n = grid[0].length;
        int m = grid.length;

        for(int i=0;i<n;i++){
            dfs(grid, i, 0);
            dfs(grid, i, n-1);
        }

        for(int j=0; j<m;j++){
            dfs(grid, 0, j);
            dfs(grid, m-1,j);
        }

        for(int i=0; i<n;i++){
            for(int j=0; j<m;j++){
                if(grid[i][j]==1)
                    count++;
            }
        }


        return count;
    }

    public static void dfs(int[][] grid, int i,int j){
        int n= grid[0].length;
        int m= grid.length;

        if(i<0 || j<0 || i>=n || j>=m || grid[i][j]==0){
            return;
        }

        grid[i][j]=0;
        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }

    /*
        https://leetcode.com/problems/number-of-islands/description/

        Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
        An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
        You may assume all four edges of the grid are all surrounded by water.
        How many connected groups of '1' exist
        We scan the grid:
        When we find a '1', we:
        Found a new island
        Increment the counter
        Then we “sink” the island using DFS:
        Visit all connected '1'
        Mark them as '0' (visited)
        👉 This ensures we don’t count the same island twice
     */
    public static int numIslands(char[][] grid){
        if(grid==null||grid.length==0) return 0;

        int m = grid.length;
        int n = grid[0].length;
        int count=0;

        for(int i=0; i<m; i++){
            for(int j=0; j<n;j++){
                if(grid[i][j]=='1'){
                    count++;
                    markTo0WithDfs(grid, i,j);
                }
            }
        }
        return count;
    }
    public static void markTo0WithDfs(char[][] grid, int i,int j){
        int m= grid.length;
        int n= grid[0].length;

        if(i<0||j<0||i>=m||j>=n|| grid[i][j]!='1'){
            return;
        }

        grid[i][j]='0';
        markTo0WithDfs(grid, i+1,j);
        markTo0WithDfs(grid, i-1,j);
        markTo0WithDfs(grid, i,j+1);
        markTo0WithDfs(grid, i,j-1);
    }

    /*
        https://leetcode.com/problems/running-sum-of-1d-array/description/
        Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
        Return the running sum of nums.
        Input: nums = [1,2,3,4]
        Output: [1,3,6,10]
        Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
     */
    public static void runningSum(int[] nums) {
        if(nums.length==0) return;

        int[] result = new int[nums.length];

        result[0] = nums[0];
        for(int i=1; i<nums.length; i++){
            result[i]=result[i-1]+nums[i];
        }

        for(int i=0; i<result.length;i++){
            System.out.print(result[i]+ " ");
        }
        System.out.println();
    }

    /*
        https://leetcode.com/problems/trapping-rain-water/description/
        Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it can trap after raining.


        1. Two pointers
        left = 0
        right = n - 1

        We process from both ends inward.
        Track max walls
        leftMax: highest wall seen from the left
        rightMax: highest wall seen from the right
        if (height[left] < height[right])
        👉 Why this matters:
        The smaller side limits the water
        If left is smaller → water depends on leftMax
        If right is smaller → water depends on rightMax
        if (height[left] >= leftMax)
            leftMax = height[left];
        else
            water += leftMax - height[left];

        👉 Meaning:

        If current bar is taller → update max
        If smaller → water gets trapped
     */
    public static int trap(int[] height) {
        int left = 0;
        int right = height.length-1;
        int maxleft =0;
        int maxright = 0;
        int water =0;

        while(left<right){
            if(height[left]<height[right]){
                if(height[left]>=maxleft){
                    maxleft = height[left];
                }else{
                    water += maxleft-height[left];
                }
                left++;
            }else {
                if(height[right]>=maxright){
                    maxright= height[right];
                } else {
                    water += maxright-height[right];
                }
                right--;
            }
        }

        return water;
    }

    /*
        https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/
        Given a 1-indexed array of integers numbers that is already sorted in non-decreasing order,
        find two numbers such that they add up to a specific target number. Let these two numbers be numbers[index1] and numbers[index2]
        where 1 <= index1 < index2 <= numbers.length.
        Return the indices of the two numbers index1 and index2, each incremented by one, as an integer array [index1, index2] of length 2.
        The tests are generated such that there is exactly one solution. You may not use the same element twice.

        Your solution must use only constant extra space.

        Input: numbers = [2,7,11,15], target = 9
        Output: [1,2]
        Explanation: The sum of 2 and 7 is 9. Therefore, index1 = 1, index2 = 2. We return [1, 2].
        This is a classic Two Pointers problem, and the key constraint is:
        ✅ The array is sorted
     */
    public static int[] twoSum(int[] numbers, int target) {
        int l= 0;
        int r = numbers.length-1;

        while(l<r){
            if(numbers[l]+numbers[r]<target){
                l++;
            } else if(numbers[l]+numbers[r]>target){
                r--;
            } else{
                return new int[]{l+1,r+1};
            }
        }
        return new int[]{};
    }

    /*
        https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/description/

        Given an integer array nums sorted in non-decreasing order, remove some duplicates in-place such that each unique element appears at most twice.
        The relative order of the elements should be kept the same.
        Since it is impossible to change the length of the array in some languages, you must instead have the result be placed in
        the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the first k elements
        of nums should hold the final result. It does not matter what you leave beyond the first k elements.
        Return k after placing the final result in the first k slots of nums.
        Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.

        This is another Two Pointers (in-place array modification) problem, but with a twist:
        Each number can appear at most twice, not once.
        Since the array is sorted, duplicates are grouped together.
        We don’t need to count explicitly—we can control how many times we write a number into the array.
     */
    public static int removeDuplicates(int[] nums) {
            int k=0;
        for(int i=0; i<nums.length;i++){

            if(k<2 || nums[i]!=nums[k-2]){
                nums[k]=nums[i];
                k++;
            }
        }
        return k;
    }

    /*
        https://leetcode.com/problems/reverse-string-ii/description/?envType=problem-list-v2&envId=string
        Given a string s and an integer k, reverse the first k characters for every 2k characters counting from the start of the string.
        If there are fewer than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters,
        then reverse the first k characters and leave the other as original.

        Input: s = "abcdefg", k = 2
        Output: "bacdfeg"
        careful exceding the s length
     */
    public static String reverseStr(String s, int k) {

        if(s.length()<=k) return new StringBuilder(s).reverse().toString();

        StringBuilder result = new StringBuilder();
        for(int i=0; i<s.length(); i= i + (k*2)){

            int left = 0;

            StringBuilder sb = new StringBuilder();
            while(left<k && left+i<s.length()){
                sb.append(s.charAt(left+i));
                left++;
            }
            result.append(sb.reverse());

            left =0;
            while(left<k && i+left+k<s.length()){
                result.append(s.charAt(left+i+k));
                left++;
            }
        }
        return result.toString();
    }

    /*
        https://leetcode.com/problems/maximum-subarray/description/

        Given an integer array nums, find the subarray with the largest sum, and return its sum.
        Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
        Output: 6
        Explanation: The subarray [4,-1,2,1] has the largest sum 6.

        We want the maximum sum of a contiguous subarray.
        “At each position, is it better to extend the previous subarray or start fresh?”
        Either:
        Start new subarray at i
        OR extend previous one
     */
    public static int maxSubArray(int[] nums) {
        int max = Integer.MIN_VALUE;
        int actualSum =0;

        for(int i:nums){
            actualSum = Math.max(actualSum+i, i);
            if(max<actualSum){
                max = actualSum;
            }
        }
        return max;
    }

    /*
        https://leetcode.com/problems/minimum-window-substring/description/

        Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that
         every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
        The testcases will be generated such that the answer is unique.
        Input: s = "ADOBECODEBANC", t = "ABC"
        Output: "BANC"
        Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

        We want the smallest substring of s that contains all characters of t (including duplicates).
        👉 This is a variable-size sliding window problem:
        Expand the window → until it's valid
        Shrink the window → to make it minimal
     */
    public static String minWindow(String s, String t) {

        Map<Character, Long> lookedMap = t.chars().mapToObj(i -> (char) i).toList()
                .stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));


        Map<Character, Long> ocurrencesFound = new LinkedHashMap<>();

        Long left=0L;
        Long right=0L;
        int min=Integer.MAX_VALUE;
        int minLeft=0;
        int minRight=0;
        boolean isValid= false;

        while(right<=s.length() || isValid){


            isValid= testValidFrequency(lookedMap, ocurrencesFound);

            if(!isValid&& right<s.length()){
                ocurrencesFound.put(s.charAt(right.intValue()),ocurrencesFound.getOrDefault(s.charAt(right.intValue()),0L)+1);
                right++;
            } else if(isValid){
                while(isValid){
                    if(min>right-left){
                        min=right.intValue()-left.intValue();
                        minLeft = left.intValue();
                        minRight= right.intValue();
                    }

                    removeCharacter(ocurrencesFound, s.charAt(left.intValue()));
                    isValid= testValidFrequency(lookedMap, ocurrencesFound);
                    left++;
                }


            } else break;
        }
        return s.substring(minLeft, minRight);
    }

    private static void removeCharacter(Map<Character, Long> ocurrencesFound, char c) {
        if(ocurrencesFound.containsKey(c)){
            if(ocurrencesFound.get(c)==1){
                ocurrencesFound.remove(c);
            } else {
                ocurrencesFound.put(c, ocurrencesFound.get(c)-1);
            }
        }
    }

    private static boolean testValidFrequency(Map<Character, Long> lookedMap, Map<Character, Long> ocurrencesFound) {
        for(Map.Entry<Character, Long> entry: lookedMap.entrySet()){
            if(!ocurrencesFound.containsKey(entry.getKey()) || ocurrencesFound.get(entry.getKey())< lookedMap.get(entry.getKey())){
                return false;
            }
        }

        return true;
    }

    /*
        https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/

        You are given a string s and an array of strings words. All the strings of words are of the same length.
        A concatenated string is a string that exactly contains all the strings of any permutation of words concatenated.
        For example, if words = ["ab","cd","ef"], then "abcdef", "abefcd", "cdabef", "cdefab", "efabcd", and "efcdab"
        are all concatenated strings. "acdbef" is not a concatenated string because it is not the concatenation of any permutation of words.
        Return an array of the starting indices of all the concatenated substrings in s. You can return the answer in any order.
        Input: s = "barfoothefoobarman", words = ["foo","bar"]
        Output: [0,9]
        The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
        The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.
        need to run from starting each letter to the right 1 by 1
        inside it starting from current index plus word length
        look for words to be found, if that is 1 looked word, then
        keep it, continue searching from the end of this word plus the
        before validating if all words are found, validate the frequency
        is correct, if not need to shrink words from the left
        when the frequency and number of words are correct, save tha index
        then shrink the left 1 word and continue the search
     */
    public static List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        if (s == null || words == null || words.length == 0) {
            return result;
        }

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        Map<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        // Try all offsets
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int count = 0;
            Map<String, Integer> window = new HashMap<>();

            for (int right = i; right + wordLen <= s.length(); right += wordLen) {

                String word = s.substring(right, right + wordLen);

                if (map.containsKey(word)) {
                    window.put(word, window.getOrDefault(word, 0) + 1);
                    count++;

                    // If too many occurrences → shrink
                    while (window.get(word) > map.get(word)) {
                        String leftWord = s.substring(left, left + wordLen);
                        window.put(leftWord, window.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                    // If valid window found
                    if (count == wordCount) {
                        result.add(left);

                        // shrink to continue searching
                        String leftWord = s.substring(left, left + wordLen);
                        window.put(leftWord, window.get(leftWord) - 1);
                        left += wordLen;
                        count--;
                    }

                } else {
                    // reset window
                    window.clear();
                    count = 0;
                    left = right + wordLen;
                }
            }
        }

        return result;
    }

    /*
        https://leetcode.com/problems/4sum/description/

        Given an array nums of n integers, return an array of all the unique quadruplets [nums[a], nums[b], nums[c], nums[d]] such that:
        0 <= a, b, c, d < n
        a, b, c, and d are distinct.
        nums[a] + nums[b] + nums[c] + nums[d] == target
        You may return the answer in any order.
        Input: nums = [1,0,-1,0,-2,2], target = 0
        Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]

        sorting para juntar los duplicados
        del lado izq necesitamos 2 numeros y 2 numeros despues
        empezamos en index 0
        i starts with 0, for following i's confirm it is not same than previous i's if it is skip
        j starts with i+1, but if it is not on the j's starting point then validate its current position is not equal than j-1
        if it is skip. we have 2 unique numbers now, now from the rest starting in the next av index from the left and the right
        from the las element, sum i, j, left and right, if the sum is equal to the target, keep it, if sum is < than target move left
        if it is bigger move right
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {

        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for(int i=0; i<nums.length-3;i++){

            if(i>0){
                if(nums[i]==nums[i-1]) continue;
            }

            for(int j=i+1; j<nums.length-2;j++){
                if(j>i+1){
                    if(nums[j]==nums[j-1]) continue;
                }

                int left=j+1;
                int right = nums.length-1;

                while(left<right){
                    long actualSum= (long)nums[i] +nums[j]+nums[left]+nums[right];
                    if(actualSum==target){
                        result.add(List.of(nums[i],nums[j],nums[left],nums[right]));
                        left++;
                        right--;
                        while(left<right && nums[left]==nums[left-1]) left++;
                        while(left < right && nums[right]==nums[right+1]) right--;

                    }else if(actualSum<target){
                        left++;
                    } else {
                        right--;
                    }
                }

            }
        }

        return result;
    }

    /*
        https://leetcode.com/problems/longest-consecutive-sequence/description/
        Given an unsorted array of integers nums, return the length of the longest consecutive elements sequence.
        You must write an algorithm that runs in O(n) time.

        O(n) time, so sorting not available
        add set for constanting time and validate if set has value -1 update counter and max

        key // Only start if it's the beginning of a sequence (if there is not a value -1 then start counting there
     */
    public static int longestConsecutive(int[] nums) {
        HashSet<Integer> set = new HashSet<>();
        int max=0;

        for(int i:nums){
            set.add(i);
        }

        for(int i:set){  //iterate set will remove the computation on duplicates not needed
            if(!set.contains(i-1)){
                int count=1;
                int increment=1;

                while(set.contains(i+increment)){
                    count++;
                    increment++;
                }
                if(count>max)
                    max =count;
            }
        }

        return max;
    }



    //TODO
    //https://leetcode.com/problems/jump-game-ii/description/

    /*
        You are given a 0-indexed array of integers nums of length n. You are initially positioned at index 0.
        Each element nums[i] represents the maximum length of a forward jump from index i. In other words, if you are at index i,
        you can jump to any index (i + j) where:
        0 <= j <= nums[i] and
        i + j < n
        Return the minimum number of jumps to reach index n - 1. The test cases are generated such that you can reach index n - 1.
        Input: nums = [2,3,1,1,4]
        Output: 2
        Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
     */

    //[2,3,1,1,4]  (initial, im at index 0 and the value is 2, so i can go to 2 places, [1,2]
    //[1] [2,3,4] if i go 1, there is a 3 with this 3, i can now go to 3 places, [2,3,4] step 1 from 0 to 1, step 2 from 1 to 4
    //[2] [3]   if instead i go 2, there is a 1, so i could get to 3
    //combining the available next options [2,3,4], but 2 already in step 1 so remove [3,4] and if 4 we got to the end
    public int jump(int[] nums) {
        return 0;
    }

    /*
    https://leetcode.com/problems/gas-station/description/

        There are n gas stations along a circular route, where the amount of gas at the ith station is gas[i].
        You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from the ith station to its next (i + 1)th station.
        You begin the journey with an empty tank at one of the gas stations.
        Given two integer arrays gas and cost, return the starting gas station's index if you can travel around the circuit
        once in the clockwise direction, otherwise return -1. If there exists a solution, it is guaranteed to be unique.
         Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
        Output: 3
        Explanation:
        Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
        Travel to station 4. Your tank = 4 - 1 + 5 = 8
        Travel to station 0. Your tank = 8 - 2 + 1 = 7
        Travel to station 1. Your tank = 7 - 3 + 2 = 6
        Travel to station 2. Your tank = 6 - 4 + 3 = 5
        Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
        Therefore, return 3 as the starting index.

    At each station, define:
    net[i] = gas[i] - cost[i]
    This represents how much gas you gain (or lose) when moving from station i to i+1.
    Total gas vs total cost
    If sum(gas) < sum(cost) → impossible to complete the circuit → return -1.
    If sum(gas) >= sum(cost) → a solution must exist (and it's unique).
    If you start at station start and at some point your tank becomes negative at station i, then:

👉 None of the stations between start and i can be a valid starting point.
    Why?
    Because:
    You already accumulated gas from start to i
    If that total failed, starting later (with less gas accumulated) will fail even sooner
    So we skip all those candidates at once.
    Iterate through all stations
    Add gas[i] - cost[i] to both totalTank and currentTank
    If currentTank < 0:
    We cannot reach station i+1 from current start
    So:
    Set start = i + 1
    Reset currentTank = 0
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int currentTank=0;
        int totalTank=0;
        int start=0;

        for(int i=0; i<gas.length;i++){
            int leftGas = gas[i]-cost[i];

            totalTank+=leftGas;
            currentTank+=leftGas;

            if(currentTank<0){
                start = i+1;
                currentTank=0;
            }

        }
        return totalTank>=0 ? start: -1;

    }

    /*
            https://leetcode.com/problems/insert-interval/description/
            you are giving an array intervals and and array overlaps, need to merge overlaps into intervals
            Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
            Output: [[1,5],[6,9]]

            it will be only one new interval, with its starting and ending interval which should fin into the
            array of intervals where corresponds
            1,5 has the original interval plus the merge of the new interval
            (arrays already sorted) existing internals dont overlap

            👉 You can solve everything in one pass by splitting the intervals into 3 groups:

            Step 1: Add all intervals that end before newInterval starts
            Step 2: Merge all overlapping intervals  (starting should be the min and ending the max of the merged values)
            Step 3: Add the merged interval
            Step 4: Add remaining intervals

     */
    public static int[][] insert(int[][] intervals, int[] newInterval) {

        int i=0;
        int n=intervals.length;
        List<int[]> result = new LinkedList<>();

        while(i<n && intervals[i][1]<newInterval[0]){
            result.add(intervals[i]);
            i++;
        }

        while(i<n && intervals[i][0]<=newInterval[1]){
            newInterval[0]= Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] =Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        result.add(newInterval);


        while(i<n && intervals[i][0]>newInterval[newInterval.length-1]){
                result.add(intervals[i]);
            i++;
        }
        return result.toArray(new int[result.size()][]);
    }
    /*
        https://leetcode.com/problems/non-overlapping-intervals/

        Given an array of intervals intervals where intervals[i] = [starti, endi], return the minimum number of intervals you need
         to remove to make the rest of the intervals non-overlapping.
        Note that intervals which only touch at a point are non-overlapping. For example, [1, 2] and [2, 3] are non-overlapping.
        Input: intervals = [[1,2],[2,3],[3,4],[1,3]]
        Output: 1
        Explanation: [1,3] can be removed and the rest of the intervals are non-overlapping.

        ordernalo, recorre ordenado, si el segundo numero del anterior arreglo (ending interval)> starting interval del siguiente
        entonces hay overlapped, + 1 a la cuenta de eliminados y considera ese arreglo eliminado, continua al siguiente

        When you detect overlap:
        if (intervals[i][0] < prevEnd)
        You remove the current interval, not the previous one.
        Why?
        Because:
        Previous interval ends earlier (due to sorting)
        Keeping it is always better (greedy choice)
     */
    public int eraseOverlapIntervals(int[][] intervals) {

        Arrays.sort(intervals, (a,b)-> Integer.compare(a[1], b[1]));

        int prevEnding = intervals[0][1];
        int count= 0;

        for(int i=1; i<intervals.length;i++){
            if(intervals[i][0]<prevEnding){
                count++;
            }else {
                prevEnding = intervals[i][1];
            }
        }
        return count;
    }
    /*
        need to be sorted, if found result will be 0 or greater, if not found it will not return -1 always, could return -3, etc, 0>= found if not return -1
     */
    public static int search(int[] nums, int target) {
        int i = Arrays.binarySearch(nums, target);
        return i>=0 ? i:-1;
    }
    /*
        https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
        There is an integer array nums sorted in non-decreasing order (not necessarily with distinct values).
        Before being passed to your function, nums is rotated at an unknown pivot index k (0 <= k < nums.length) such that
        the resulting array is [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]] (0-indexed).
        For example, [0,1,2,4,4,4,5,6,6,7] might be rotated at pivot index 5 and become [4,5,6,6,7,0,1,2,4,4].
        Given the array nums after the rotation and an integer target, return true if target is in nums, or false if it is not in nums.
        You must decrease the overall operation steps as much as possible.

        IMPORTANT mid is checked already at the beginning so when comparing target with nums[mid] no need to <= or >=, only < or > will
        suffice. on the other hand for nums[left], nums[right]. it does need to validate <= or >= when comparing to target

     */
    public boolean searcha(int[] nums, int target) {
        int n = nums.length;
        int left =0;
        int right=n-1;

        while(left<=right){
            int mid = left + (right-left)/2;

            if(nums[mid]== target){return true; }

            if(nums[left]==nums[mid] && nums[right]==nums[mid]){
                left++;
                right--;
            }

            else if(nums[left]<=nums[mid]){
                if(nums[left]<=target && target <nums[mid]){
                    right= mid-1;
                }else{
                    left=mid+1;
                }
            }
            else {
                if(nums[mid]<target && target<=nums[right]){
                    left=mid+1;
                } else {
                    right=mid-1;
                }
            }
        }
        return false;
    }

    /*
        Given an array a, your task is to output an array b of the same length by applying the following transformation:
        – For each i from 0 to a.length - 1 inclusive, b[i] = a[i - 1] + a[i] + a[i + 1]
        – If an element in the sum a[i - 1] + a[i] + a[i + 1] does not exist, use 0 in its place
        – For instance, b[0] = 0 + a[0] + a[1]

        For a = [4, 0, 1, -2, 3]:
        – b[0] = 0 + a[0] + a[1] = 0 + 4 + 0 = 4
        – b[1] = a[0] + a[1] + a[2] = 4 + 0 + 1 = 5
        – b[2] = a[1] + a[2] + a[3] = 0 + 1 + (-2) = -1
        – b[3] = a[2] + a[3] + a[4] = 1 + (-2) + 3 = 2
        – b[4] = a[3] + a[4] + 0 = (-2) + 3 + 0 = 1
        So, the output should be solution(a) = [4, 5, -1, 2, 1]
     */
    public static void arrayManipulation(int[] a){
        int[] b = new int[a.length];
        for(int i=0; i<a.length;i++){
            int sum=0;
            if(i>0){
                sum+=a[i-1];
            }
            sum+=a[i];

            if(i<a.length-1){
                sum+=a[i+1];
            }
            b[i]=sum;
        }

        for(int i=0; i<b.length;i++){
            System.out.print(b[i] +" ");
        }
        System.out.println();
    }

    /**
     * https://codesignal.com/blog/example-codesignal-questions/
     *Question 2: String Pattern Matching
     * You are given two strings: pattern and source. The first string pattern contains only the symbols 0 and 1, and the second string source contains only lowercase English letters.
     *
     * Your task is to calculate the number of substrings of source that match pattern.
     *
     * We’ll say that a substring source[l..r] matches pattern if the following three conditions are met:
     * – The pattern and substring are equal in length.
     * – Where there is a 0 in the pattern, there is a vowel in the substring.
     * – Where there is a 1 in the pattern, there is a consonant in the substring.
     *
     * Vowels are ‘a‘, ‘e‘, ‘i‘, ‘o‘, ‘u‘, and ‘y‘. All other letters are consonants.
     *
     * Example
     *
     * For pattern = "010" and source = "amazing", the output should be solution(pattern, source) = 2.
     * – “010” matches source[0..2] = "ama". The pattern specifies “vowel, consonant, vowel”. “ama” matches this pattern: 0 matches a, 1 matches m, and 0 matches a.
     * – “010” doesn’t match source[1..3] = "maz"
     * – “010” matches source[2..4] = "azi"
     * – “010” doesn’t match source[3..5] = "zin"
     * – “010” doesn’t match source[4..6] = "ing"
     *
     * So, there are 2 matches.
     */
    public static void stringPatternMatching(String s, String pattern){
        int vowelsCount=0;
        Set<Character> vowels = new HashSet<>(){{add('a');add('e');add('i');add('o');add('u');}};
        int start=0;
        int vowelsInPattern =(int) pattern.chars().mapToObj(i->(char)i).filter(c->c=='0').count();
        int matches = 0;

        for(int i=0; i<s.length();i++){
            if(i>=pattern.length()){
                if(vowelsCount==vowelsInPattern){
                    matches++;
                }

                if(vowels.contains(s.charAt(start))){
                    vowelsCount--;
                }
                if(vowels.contains(s.charAt(i))){
                    vowelsCount++;
                }
                start++;

            }else{
                if(vowels.contains(s.charAt(i))){
                    vowelsCount++;
                }
            }

        }
        System.out.println("Matches: " +matches);

    }

    /*
        https://leetcode.com/problems/longest-palindromic-substring/
        Given a string s, return the longest palindromic substring in s.

        Input: s = "babad"
        Output: "bab"
        Explanation: "aba" is also a valid answer.

        Optimal idea: Expand Around Center

        Every palindrome is centered around:

        one character (odd length, like "aba")
        between two characters (even length, like "abba")

        So instead of generating substrings, you expand from centers.
        Core intuition

        For each index i, try:

        Expand from (i, i) → odd length
        Expand from (i, i+1) → even length

        Keep track of the longest palindrome found.
     */
    public static String longestPalindrome(String s) {

        int start=0;
        int end=0;
        for(int i=0; i<s.length();i++){
            int lenOdd=checkLength(s,i,i); //odd case
            int lenEven=checkLength(s,i,i+1); //even case
            int len= Math.max(lenOdd,lenEven);

            //queremos ir desce el centro (lo controla i) hacia atras la mitad y hacia adelante la mitad
            //-1 por q el substring si incluye el primer punto pero el end es excluyente
            if(len>end-start){
                start= i - (len-1)/2;
                end = i+(len/2);
            }

        }

        return s.substring(start,end+1);
    }

    private static int checkLength(String s, int left, int right){

        //si son iguales ve expandiendo desde el centro 1 a cada lado
        while(left<=right && left>=0 && right <s.length() && s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        //right esta 1 posicion de mas a la derecha y left 1 de mas a la izquierda para salir del while
        return right-left -1;
    }

    /*
        Given a signed 32-bit integer x, return x with its digits reversed.
        If reversing x causes the value to go outside the signed 32-bit integer range [-231, 231 - 1], then return 0.
        Assume the environment does not allow you to store 64-bit integers (signed or unsigned).
     */
    public static int reverse(int x){
        String value = String.valueOf(x);
        if(value.charAt(0)=='-') value=value.substring(1);
        String reversedValue = new StringBuilder(value).reverse().toString();

        int len=value.length();
        int result=0;
        try{
            int fit= Integer.parseInt(reversedValue);
            for(int i=0; i<len;i++){
                result = (result*10) + x%10;
                x=x/10;
            }
        }catch(Exception e){

        }



        return result;
    }
    /*
        https://leetcode.com/problems/3sum/description/


        Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]] such that i != j,
        i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
        Notice that the solution set must not contain duplicate triplets.

        Example 1:

        Input: nums = [-1,0,1,2,-1,-4]
        Output: [[-1,-1,2],[-1,0,1]]
        Explanation:
        nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
        nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
        nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
        The distinct triplets are [-1,0,1] and [-1,-1,2].
        Notice that the order of the output and the order of the triplets does not matter.
     */
    public static List<List<Integer>> threeSum(int[] nums) {

       Arrays.sort(nums);
       List<List<Integer>> result = new ArrayList<>();

       for(int i=0; i<nums.length; i++){

           if(i>0 && nums[i]==nums[i-1]) continue;

           int left = i+1;
           int right = nums.length-1;

           while(left<right){
               int sum= nums[i]+nums[left]+nums[right];
               if(sum==0){
                   result.add(new ArrayList<>(List.of(nums[i],nums[left],nums[right])));

                   left++;
                   right--;

                   while(left<right && nums[left]==nums[left-1]) left++;
                   while(right>left && nums[right]==nums[right+1]) right--;
               }else if(sum<0){
                   left++;
               } else {
                   right--;
               }
           }
       }
       return result;
    }

    /*
        write a java program to group anagrams from a list of strings,
        input: "eat", "tea", "tan","ate","nat","bat",
        output:[["eat", "tea", "ate"], ["tan","nat"], ["bat"]

        Two strings are anagrams if they contain the same characters with the
        same frequency (just in different order), like "eat" and "tea"
        key sorted and compare next keys sorted, value the actual string before sort
     */
    public static Map<String, Set<String>> groupAnagrams(List<String> list){
        Map<String, Set<String>> map = new HashMap<>();

        for(String s: list){
            char[] sortedArray = s.toCharArray();
            Arrays.sort(sortedArray);
            String sorted = new String(sortedArray);

            if(map.containsKey(sorted)){
                Set<String> current = map.get(sorted);
                current.add(s);
                map.put(sorted, current);
            }
            else{
                map.put(sorted, new HashSet<>(){{add(s);}});
            }
        }

        return map;
    }

    /*
        Write a program to print all the permutations of a given string.
        A String of n characters can have !n (factorial of n) permutations.
        For example a String of 3 characters like "xyz" has 6 possible permutations xyz, xzy, yxz, yzx, zxy, zyx.
        Note: The results have to be one word per line and they should be in a sorted order alphabetically.
        Example input:  ok   Output: ko, ok
     */

    public static void printPermutations(String s){

        char [] array = s.toCharArray();
        Arrays.sort(array);

        permute(array, 0);

    }

    private static void permute(char[] array, int index){
            if(array.length==index){
                System.out.println(new String(array));
                return;
            }

            for(int i=index; i<array.length;i++){

                swap(array, index,i);
                permute(array, index+1);
                swap(array, index,i);
            }
    }

    private static void swap(char[] array, int start, int end){
        char temp= array[start];
        array[start] = array[end];
        array[end] = temp;
    }

    /*
        The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this:
        (you may want to display this pattern in a fixed font for better legibility)
        P   A   H   N
        A P L S I I G
        Y   I   R
        And then read line by line: "PAHNAPLSIIGYIR"

        another way than implemented list of string builder
        move rows up or down as needed when 0 going down when numrows-1 going up
        appending has the column in order

     */
    public static String convert(String s, int numRows) {

        if(s.length()<=numRows || numRows==1) return s;

        char[] array = s.toCharArray();
        char[][] result = new char[s.length()][s.length()];

        for(int k=0; k<result.length;k++){
            for(int l=0; l<result.length;l++){
                result[k][l]=' ';
            }

        }

        boolean reach = false;
        int j=0;
        int count=0;
        int i=0;
        while(count<s.length()){
            result[i][j] = array[count++];
            if(reach){
                if(i<=0){
                    reach=false;
                    i++;
                }else{
                    j++;
                    i--;
                }
            }else if(i==numRows-1){
                reach=true;
                j++;
                i--;
            }else {
                i++;
            }

        }

        StringBuilder sb = new StringBuilder();
        for(int k=0; k<numRows;k++){
            for(int l=0; l<result.length;l++){
                if(result[k][l]!=' '){
                    sb.append(result[k][l]);
                }
            }

        }
        return sb.toString();
    }

    /*
        Integer to Roman
        Store values in descending order
        Loop through them
        While num >= value:
        append symbol
        subtract value
     */
    public static String intToRoman(int num) {
        int[] values = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] symbol = new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder result = new StringBuilder();
        for(int i=0;i<values.length;i++){

            while(num>=values[i]){
                result.append(symbol[i]);
                num= num-values[i];
            }
        }
        return result.toString();
    }

    /*
        Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
        Example 1:
        Input: n = 3
        Output: ["((()))","(()())","(())()","()(())","()()()"]
        Example 2:
        Input: n = 1
        Output: ["()"]

        At any point, you can:
        Add "(" → if you still have some left
        Add ")" → only if it won’t break validity

        Let:
        open = number of ( used
        close = number of ) used
        We must ensure:
        1. open <= n
        2. close <= open   ← THIS is the key rule
     */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        backtrackParenthesis(result, "", 0 ,0,n);
        return result;
    }

    private static void backtrackParenthesis(List<String> result, String current, int open, int close, int n) {

        if(current.length()==n*2){
            result.add(current);
            return;
        }

        if(open<n){
            backtrackParenthesis(result, current+"(",open+1,close,n);
        }

        if(close<open){
            backtrackParenthesis(result, current+")", open, close+1, n);
        }
    }


}


