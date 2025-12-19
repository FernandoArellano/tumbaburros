package com.example.tumbaburros.exercizes.chatgpt;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    //Enunciado
    //
    //Dada una lista de enteros, obtener los números pares y multiplicarlos por 2.
    public static void getMultipliedPairs(){
        System.out.println(IntStream.rangeClosed(0,20).boxed().filter(x->x%2==0).map(x->x*2).collect(Collectors.toList()));
    }

    //Enunciado
    //
    //Dada una lista de números, eliminar duplicados y ordenarla.
    public static void sortedAndUnique(){
        System.out.println(List.of(5,8,7,9,2,1,2,3,4,5,6,4).stream().distinct().sorted(Comparator.naturalOrder()).collect(Collectors.toList()));
    }

    //Enunciado
    //
    //Encontrar el número máximo de una lista.
    public static void max(){
        System.out.println(List.of(5,8,7,9,2,1,2,3,4,5,6,4).stream().max(Comparator.naturalOrder()).get());
    }

    //Enunciado
    //
    //Calcular la suma total de una lista.
    public static void reduce(){
        System.out.println(List.of(5,8,7,9,2,1,2,3,4,5,6,4).stream().reduce(0, (integer, integer2) -> integer+integer2).doubleValue());
    }

    //Concatenate Strings with reduce
    public static void reduce2(){
        System.out.println(List.of("Jose", "Fernando", "Arellano", "Saldaña").stream().reduce("", (s1,s2) -> s1 +" " +s2));

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        Optional<Integer> max = numbers.stream().reduce(Integer::max);
    }

    //Enunciado
    //
    //Dada una lista de empleados, crear un mapa id → empleado
    public static void mapearEmpleado(){
        Map<Integer, Empleado> collect = List.of(new Empleado("isra", 4), new Empleado("fer", 1), new Empleado("ady", 2), new Empleado("pau", 3))
                .stream().collect(Collectors.toMap(Empleado::id, Function.identity(), (old, news)->news, LinkedHashMap::new));

        System.out.println(collect);

        collect.entrySet().stream().sorted(Map.Entry.comparingByKey()).forEach(System.out::println);
    }

    //Enunciado
    //
    //Separar empleados en dos grupos: sueldo mayor a 5000 y el resto
    public static void separarPorSueldo(){
        Map<Boolean, List<Employee>> collect = List.of(new Employee(1, "fer", 3000, "it"),
                        new Employee(2, "fer2", 300, "it"),
                        new Employee(3, "fer3", 30000, "internal"),
                        new Employee(4, "fer4", 300000, "rh"),
                        new Employee(5, "fer5", 3000000, "admin"))
                .stream()
                .collect(Collectors.partitioningBy(e -> e.salary() > 3000));
        System.out.println(collect);
    }

    //Enunciado
    //
    //Obtener el segundo salario más alto.
    public static void segundoMasAlto(){
        List.of(new Employee(1, "fer", 3000, "it"),
                        new Employee(2, "fer2", 300, "it"),
                        new Employee(3, "fer3", 30000, "internal"),
                        new Employee(4, "fer4", 300000, "rh"),
                        new Employee(5, "fer5", 3000000, "admin"))
                .stream()
                .sorted(Comparator.comparing(Employee::salary).reversed())
                .skip(1)
                .findFirst().ifPresent(System.out::println);


    }

    //Validar si todos los empleados ganan más de 3000.
    public static void validarTodos(){
        boolean result = List.of(new Employee(1, "fer", 3000, "it"),
                        new Employee(2, "fer2", 300, "it"),
                        new Employee(3, "fer3", 30000, "internal"),
                        new Employee(4, "fer4", 300000, "rh"),
                        new Employee(5, "fer5", 3000000, "admin"))
                .stream()
                .allMatch(employee -> employee.salary()>3000);

        System.out.println("Ganan todos mas de 3000?:" +result);
    }

    //Dado un Map<String, Integer>, obtener las claves con valor mayor a 100.
    public static void keysConValorArribaDe100(){
        Map.of( "A",100, "B",200, "C", 30, "D",20, "E",1000)
                .entrySet().stream()
                .filter(e -> e.getValue()>100)
                .map(e->e.getKey())
                .collect(Collectors.toSet()).forEach(System.out::println);

    }

    //Enunciado
    //
    //Convertir un Map<String, Employee> en Map<String, String> con nombre del empleado.
    public static void convertirMapa(){
        Map<String, String> collect = Map.of("fer", new Employee(1, "fer", 3000, "it"),
                        "fer2", new Employee(2, "fer2", 300, "it"),
                        "fer3", new Employee(3, "fer3", 30000, "internal"),
                        "fer4", new Employee(4, "fer4", 300000, "rh"),
                        "fer5", new Employee(5, "fer5", 3000000, "admin"))
                .entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().name()));

        System.out.println(collect);
    }

    //Enunciado
    //
    //Obtener una lista inmodificable de nombres de empleados.
    public static void immutableListFromList(){
        List<String> names=List.of(new Employee(1, "fer", 3000, "it"),
                new Employee(2, "fer2", 300, "it"),
                new Employee(3, "fer3", 30000, "internal"),
                new Employee(4, "fer4", 300000, "rh"),
                new Employee(5, "fer5", 3000000, "admin"))
                .stream()
                .map(e->e.name())
                .collect(Collectors.collectingAndThen(Collectors.toList(),List::copyOf));
        System.out.println(names);

    }

    //Enunciado
    //
    //Calcular mínimo y máximo salario en un solo stream.
    public static void minAndMax(){
        MinMax result = List.of(new Employee(1, "fer", 3000, "it"),
                new Employee(2, "fer2", 300, "it"),
                new Employee(3, "fer3", 30000, "internal"),
                new Employee(4, "fer4", 300000, "rh"),
                new Employee(5, "fer5", 3000000, "admin"))
                .stream()
                .collect(Collectors.teeing(
                        Collectors.minBy(Comparator.comparing(Employee::salary)),
                        Collectors.maxBy(Comparator.comparing(Employee::salary)),
                        (min, max) -> new MinMax(
                                min.orElseThrow().salary(),
                                max.orElseThrow().salary()
                        )
                ));

        System.out.println(result);
    }



    public static void main(String[] args) {
        getMultipliedPairs();
        sortedAndUnique();
        max();
        reduce();
        reduce2();
        mapearEmpleado();
        separarPorSueldo();
        segundoMasAlto();
        validarTodos();
        keysConValorArribaDe100();
        convertirMapa();
        immutableListFromList();
        minAndMax();
    }

}
