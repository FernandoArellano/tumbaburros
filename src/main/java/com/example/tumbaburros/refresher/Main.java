package com.example.tumbaburros.refresher;

import com.example.tumbaburros.streams.DataSource;
import com.example.tumbaburros.streams.Pedido;
import com.example.tumbaburros.streams.Producto;
import com.example.tumbaburros.streams.Usuario;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        //from a list of strings, join all words separated by space with , considering that each element in the list might have more than 1 word
        List<String> sentences = Arrays.asList("hello world", "java is awesome");
        System.out.println(sentences);


        //get 10 random numbers from 0 to 999 and add 10 to each one of them, then print the list



        //from a list of emails get a map of the dominion (.com) as kwy and the elements which have that dominion as value
        List<String> correos = List.of("foo@bar.com", "baz@bar.com", "foo@baz.com");


        //get the longest word from a list of strings
        List<String> palabras = List.of("stream", "java", "colecciones", "api");



        //from a list of names, group them into initial letter as key and the and the longest word with that initial as value
        List<String> nombres3 = List.of("Ana", "Alberto", "Bruno", "Benito");


        //convert list of numbers into sorted set using treeset
        List<Integer> nums = List.of(3, 1, 4, 2, 3);


        //get sum of cuadratic values from list (2*2)+(3*3...
        List<Integer> nums2 = List.of(2, 3, 4);



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
    }


    // 4. Listar todos los pedidos realizados (sin duplicados).
    public static List<Pedido> getTodosLosPedidos(List<Usuario> usuarios) {
        return null;
    }

    // 5. Obtener la suma total gastada por un usuario específico (por nombre).
    public static double getTotalGastadoPorUsuario(List<Usuario> usuarios, String nombre) {
        return 0;

    }

    // 6. Listar todos los productos comprados de la categoría "Tecnología".
    public static List<Producto> getProductosTecnologia(List<Usuario> usuarios) {
        return null;
    }

    // 7. Obtener el promedio de edad de los usuarios.
    public static double getPromedioEdad(List<Usuario> usuarios) {
        return 0;
    }


    // 10. Contar cuántos usuarios tienen más de 1 pedido.
    public static long contarUsuariosConMasDeUnPedido(List<Usuario> usuarios) {
        return 0;
    }

    // 11. Obtener el producto más barato comprado.
    public static Optional<Producto> getProductoMasBarato(List<Usuario> usuarios) {
        return null;
    }

    // 12. Crear un mapa usuario->total gastado.
    public static Map<String, Double> getTotalPorUsuario(List<Usuario> usuarios) {
        return null;
    }

    // 13. Obtener la lista de nombres de usuarios que han comprado productos de más de 500.
    public static List<String> getUsuariosConComprasCaras(List<Usuario> usuarios) {
        return null;
    }



    // 16. Calcular el pedido más antiguo realizado.
    public static Optional<Pedido> getPedidoMasAntiguo(List<Usuario> usuarios) {
        return null;
    }


    // 19. Contar cuántos productos ha comprado en total el sistema.
    public static long contarProductosTotales(List<Usuario> usuarios) {
        return 0;
    }


    // 21. Obtener el promedio de precio de productos por categoría.
    public static Map<String, Double> getPromedioPrecioPorCategoria(List<Usuario> usuarios) {
        return null;
    }

    // 25. Obtener el gasto total de todos los pedidos.
    public static double getGastoTotalSistema(List<Usuario> usuarios) {
        return 0;
    }

    // 27. Verificar si todos los usuarios tienen al menos un pedido.
    public static boolean todosTienenPedidos(List<Usuario> usuarios) {
        return false;
    }

    // 28. Encontrar el usuario con más gasto total.
    public static Optional<Usuario> getUsuarioConMasGasto(List<Usuario> usuarios) {
        return null;
    }


    /*
         You are given two strings word1 and word2. Merge the strings by adding letters in alternating order, starting with word1.
         If a string is longer than the other, append the additional letters onto the end of the merged string.
         Return the merged string.
     */
    public static String mergeAlternately(String word1, String word2) {
        return null;
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



        return result;
    }

    /*
          Given a string s, reverse only all the vowels in the string and return it.
          The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both lower and upper cases, more than once.

          stack vowels only and pop in reverse order when vowel found

          ex: System.out.println(reverseVowels("hello"));
       */
    public static String reverseVowels(String s) {
        return null;
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

        return null;
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

        return 0;
    }

    /*
   Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
   Note that you must do this in-place without making a copy of the array.

   ex: moveZeroes(new int[]{0,1,0,3,12});
*/
    public static void moveZeroes(int[] nums) {

    }


    //check anagram (if 2 words have the same characters)
    public static boolean isAnagram(String a, String b) {
        return false;
    }

    //Implement Stack Using Array
    static class ArrayStack {
        private int[] arr = new int[10];

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

    }
}
