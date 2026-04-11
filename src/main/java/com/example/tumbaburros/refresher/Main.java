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

}


