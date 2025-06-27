package com.example.tumbaburros.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        //flat map

        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9)
        );

        System.out.println(listOfLists);

        List<Integer> flattenedList = listOfLists.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(flattenedList);


        List<String> sentences = Arrays.asList("hello world", "java is awesome");

        System.out.println(sentences);

        List<String> words = sentences.stream()
                .flatMap(sentence -> Stream.of(sentence.split(" ")))
                .collect(Collectors.toList());

        System.out.println("Words: " + words);


        //generate
        List<Integer> integerList = Stream.generate(new Random()::nextInt)
                .limit(10).map(integer -> integer-10 ).collect(Collectors.toList());
        System.out.println(integerList);

        //iterate
        Stream<Integer> iterate = Stream.iterate(2, num -> num<50, num -> num*=num);
        System.out.println(iterate.collect(Collectors.toList()));

        //joining strings
        List<String> grupos = List.of("Banda A", "Banda B", "Banda C");
        StringJoiner joiner = new StringJoiner(",");
        StringBuilder sb = new StringBuilder();

        grupos.stream().forEach(joiner::add);

        System.out.println(sb.append(joiner));

        //better way
        String result = grupos.stream().collect(Collectors.joining(","));
        System.out.println(result);

        //int stream sum
        List<Integer> enteros = List.of(1, 2, 3, 4, 5);
        System.out.println(enteros.stream().mapToInt(i-> i).sum());

        //grouping by
        List<String> correos = List.of("foo@bar.com", "baz@bar.com", "foo@baz.com");
        Map<String, List<String>> collect = correos.stream().collect(Collectors.groupingBy(c -> c.split("@")[1]));
        System.out.println(collect);

        //longest word comparing method
        List<String> palabras = List.of("stream", "java", "colecciones", "api");
        String longestword = palabras.stream().max(Comparator.comparing(String::length)).orElse("");
        System.out.println(longestword);

        //flatmap
        List<List<String>> listas = List.of(List.of("a", "b"), List.of("c", "d"));
        List<String> aplanada = listas.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(aplanada);

        //collectors grouping by
        List<String> palabras2 = List.of("java", "stream", "java", "code");
        System.out.println(palabras2.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

        //collect people by age
        // personas.stream().collect(Collectors.groupingBy(Persona::getEdad))

        //lista de personas a mapa de personas con edad
        // personas.stream().collect(Collectors.toMap(Persona::getNombre, Persona::getEdad))

        //agrupar por inicial
        List<String> nombres = List.of("Ana", "Alma", "Bruno", "Beto");
        System.out.println(nombres.stream().collect(Collectors.groupingBy(s-> s.toCharArray()[0])));

        //sumar por concepto
        // compras.stream().collect(Collectors.groupingBy(Compra::getCategoria, Collectors.summingDouble(Compra::getMonto)))

        //second biggest
        // nums.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(-1)

        //convert into sorted set
        List<Integer> nums = List.of(3, 1, 4, 2, 3);
        System.out.println(nums.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toSet()));

        //group by length and counting
        List<String> palabras3 = List.of("uno", "dos", "tres", "cuatro");
        System.out.println(palabras3.stream().collect(Collectors.groupingBy(String::length, Collectors.counting())));

        //reduce suma de los cuadrados
        List<Integer> nums2 = List.of(2, 3, 4);
        System.out.println(nums2.stream().map(n -> n * n).reduce(0, Integer::sum));

        //multiply all elements (reduce)
        List<Integer> valores = List.of(2, 3, 4);
        System.out.println(valores.stream().reduce(1, (a, b) -> a * b));

        //get map initial and longest word of that initial
        List<String> nombres3 = List.of("Ana", "Alberto", "Bruno", "Benito");

        System.out.println(nombres3.stream()
                .collect(Collectors.groupingBy(n -> n.charAt(0),
                      Collectors.collectingAndThen(
                      Collectors.maxBy(Comparator.comparingInt(String::length)),
                      Optional::get))));

        //create a set from a list
        List<Integer> nums3 = List.of(9,1, 2, 2, 3);
        Set<Integer> set = nums3.stream().collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set);

        //create map with starting letter and the values which start with it
        List<String> nombres2 = List.of("Ana", "Alberto", "Bruno", "Bea");
        Map<Character, List<String>> collect1 = nombres2.stream().collect(Collectors.groupingBy(n -> n.charAt(0)));
        System.out.println(collect1);


        //find first starting with letter p
        List<String> nombres4 = List.of("Juan", "Pedro", "Ana");
        System.out.println(nombres4.stream().filter(s->s.startsWith("P")).findFirst().orElse(""));

        //merge lists
        List<List<String>> listas2 = List.of(List.of("a", "b"), List.of("c"));
        List<String> collect2 = listas2.stream().flatMap(List::stream).collect(Collectors.toList());
        System.out.println(collect2);

        List<String> nombres5 = List.of("Ana", "Pedro");
        Map<String, Integer> collect3 = nombres5.stream().collect(Collectors.toMap(Function.identity(), String::length));
        System.out.println(collect3);
    }
}
