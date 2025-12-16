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

       // System.out.println(isValid("ibfdgaeadiaefgbhbdghhhbgdfgeiccbiehhfcggchgghadhdhagfbahhddgghbdehidbibaeaagaeeigffcebfbaieggabcfbiiedcabfihchdfabifahcbhagccbdfifhghcadfiadeeaheeddddiecaicbgigccageicehfdhdgafaddhffadigfhhcaedcedecafeacbdacgfgfeeibgaiffdehigebhhehiaahfidibccdcdagifgaihacihadecgifihbebffebdfbchbgigeccahgihbcbcaggebaaafgfedbfgagfediddghdgbgehhhifhgcedechahidcbchebheihaadbbbiaiccededchdagfhccfdefigfibifabeiaccghcegfbcghaefifbachebaacbhbfgfddeceababbacgffbagidebeadfihaefefegbghgddbbgddeehgfbhafbccidebgehifafgbghafacgfdccgifdcbbbidfifhdaibgigebigaedeaaiadegfefbhacgddhchgcbgcaeaieiegiffchbgbebgbehbbfcebciiagacaiechdigbgbghefcahgbhfibhedaeeiffebdiabcifgccdefabccdghehfibfiifdaicfedagahhdcbhbicdgibgcedieihcichadgchgbdcdagaihebbabhibcihicadgadfcihdheefbhffiageddhgahaidfdhhdbgciiaciegchiiebfbcbhaeagccfhbfhaddagnfieihghfbaggiffbbfbecgaiiidccdceadbbdfgigibgcgchafccdchgifdeieicbaididhfcfdedbhaadedfageigfdehgcdaecaebebebfcieaecfagfdieaefdiedbcadchabhebgehiidfcgahcdhcdhgchhiiheffiifeegcfdgbdeffhgeghdfhbfbifgidcafbfcd"));
        //System.out.println(isValid("aaaabbcc"));
        System.out.println(unrepeated("Jose Fernando Arellano"));

        List<Usuario> usuarios = DataSource.getUsuarios();
        System.out.println(getNombresMayusculas(usuarios));
        System.out.println(getUsuariosMayoresDe30(usuarios));
        System.out.println(getEmailsUnicos(usuarios));
        System.out.println(getTodosLosPedidos(usuarios));
        System.out.println(getTotalGastadoPorUsuario(usuarios, "Carlos"));
        System.out.println(getProductosTecnologia(usuarios));
        System.out.println(getPromedioEdad(usuarios));
        System.out.println(getTop3ProductosMasCaros(usuarios));
        System.out.println(agruparProductosPorCategoria(usuarios));
        System.out.println(contarUsuariosConMasDeUnPedido(usuarios));
        System.out.println(getProductoMasBarato(usuarios));
        System.out.println(getTotalPorUsuario(usuarios));
        System.out.println(getUsuariosConComprasCaras(usuarios));
        System.out.println(getCategoriasUnicas(usuarios));
        System.out.println(ordenarUsuariosPorFecha(usuarios));
        System.out.println(getPedidoMasAntiguo(usuarios));
        System.out.println(existeCompraMayorA1000(usuarios));
        System.out.println(getNombresSeparadosPorComa(usuarios));
        System.out.println(contarProductosTotales(usuarios));
        System.out.println(agruparPedidosPorUsuario(usuarios));
        System.out.println(getPromedioPrecioPorCategoria(usuarios));
        System.out.println(getPedidoConMasProductos(usuarios));
        System.out.println(getNombresProductosUnicos(usuarios));
        System.out.println(getGastoTotalSistema(usuarios));
        System.out.println(getProductosOrdenadosPorPrecio(usuarios));
        System.out.println(todosTienenPedidos(usuarios));
        System.out.println(getUsuarioConMasGasto(usuarios));
        System.out.println(contarProductosPorCategoria(usuarios));
    }

   public static Character unrepeated(String s){

        Stream<Character>stream = s.chars().mapToObj(i-> (char)i);
        Map<Character, Long> map = stream.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
        for(Map.Entry<Character, Long> entry : map.entrySet()){
            if(entry.getValue() == 1){
                return entry.getKey();
            }
        }
        return null;
   }


    // 1. Obtener todos los nombres de usuarios en mayúsculas.
    public static List<String> getNombresMayusculas(List<Usuario> usuarios) {
        return usuarios.stream().map(u->u.getNombre().toUpperCase()).collect(Collectors.toList());
    }

    // 2. Filtrar usuarios mayores de 30 años.
    public static List<Usuario> getUsuariosMayoresDe30(List<Usuario> usuarios) {
        return usuarios.stream().filter(u->u.getEdad()>30).collect(Collectors.toList());
    }

    // 3. Obtener los emails únicos de todos los usuarios.
    public static Set<String> getEmailsUnicos(List<Usuario> usuarios) {
        return usuarios.stream().map(u->u.getEmail()).collect(Collectors.toSet());
    }

    // 4. Listar todos los pedidos realizados (sin duplicados).
    public static List<Pedido> getTodosLosPedidos(List<Usuario> usuarios) {
        return usuarios.stream().flatMap(u->u.getPedidos().stream()).distinct().collect(Collectors.toList());
    }

    // 5. Obtener la suma total gastada por un usuario específico (por nombre).
    public static double getTotalGastadoPorUsuario(List<Usuario> usuarios, String nombre) {
        return usuarios.stream().filter(u-> u.getNombre().equals(nombre))
                .flatMap(usuario -> usuario.getPedidos().stream())
                .flatMap(pedido -> pedido.getProductos().stream())
                .mapToDouble(producto -> producto.getPrecio())
                .sum();

    }

    // 6. Listar todos los productos comprados de la categoría "Tecnología".
    public static List<Producto> getProductosTecnologia(List<Usuario> usuarios) {
        return usuarios.stream().flatMap(usuario -> usuario.getPedidos().stream())
                .flatMap(pedido -> pedido.getProductos().stream())
                .filter(producto -> producto.getCategoria().equals("Tecnología"))
                .collect(Collectors.toList());
    }

    // 7. Obtener el promedio de edad de los usuarios.
    public static double getPromedioEdad(List<Usuario> usuarios) {
        return usuarios.stream().mapToDouble(u -> u.getEdad()).average().getAsDouble();
    }

    // 8. Listar los 3 productos más caros comprados.
    public static List<Producto> getTop3ProductosMasCaros(List<Usuario> usuarios) {
        return usuarios.stream()
                .flatMap(u->u.getPedidos().stream())
                .flatMap(p->p.getProductos().stream())
                .distinct()
                .sorted(Comparator.comparing(Producto::getPrecio).reversed())
                .limit(3)
                .collect(Collectors.toList());
    }

    // 9. Agrupar productos por categoría.
    public static Map<String, List<Producto>> agruparProductosPorCategoria(List<Usuario> usuarios) {
        return usuarios.stream()
                .flatMap(usuario -> usuario.getPedidos().stream())
                .flatMap(pedido -> pedido.getProductos().stream())
                .collect(Collectors.groupingBy(producto -> producto.getCategoria()));
    }

    // 10. Contar cuántos usuarios tienen más de 1 pedido.
    public static long contarUsuariosConMasDeUnPedido(List<Usuario> usuarios) {
        return usuarios.stream()
                .filter(usuario -> usuario.getPedidos().size()>1)
                .count();
    }

    // 11. Obtener el producto más barato comprado.
    public static Optional<Producto> getProductoMasBarato(List<Usuario> usuarios) {
        return usuarios.stream()
                .flatMap(usuario -> usuario.getPedidos().stream())
                .flatMap(pedido -> pedido.getProductos().stream())
                .sorted(Comparator.comparing(Producto::getPrecio))
                .limit(1)
                .findFirst();
    }

    // 12. Crear un mapa usuario->total gastado.
    public static Map<String, Double> getTotalPorUsuario(List<Usuario> usuarios) {
        return usuarios.stream()
                .collect(Collectors.toMap(
                        usuario -> usuario.getNombre(),
                        usuario -> usuario.getPedidos().stream()
                                .flatMap(pedido -> pedido.getProductos().stream())
                                .mapToDouble(Producto::getPrecio)
                        .sum()
                ));
    }

    // 13. Obtener la lista de nombres de usuarios que han comprado productos de más de 500.
    public static List<String> getUsuariosConComprasCaras(List<Usuario> usuarios) {
        return usuarios.stream()
                .filter(
                        usuario -> usuario.getPedidos().stream()
                                .flatMap(pedido -> pedido.getProductos().stream())
                                .anyMatch(producto -> producto.getPrecio()>1000)
                )
                .map(Usuario::getNombre)
                .collect(Collectors.toList());
    }

    // 14. Generar un Set con todas las categorías de productos comprados.
    public static Set<String> getCategoriasUnicas(List<Usuario> usuarios) {
        return usuarios.stream().flatMap(usuario -> usuario.getPedidos().stream()).flatMap(pedido -> pedido.getProductos().stream()).map(Producto::getCategoria).collect(Collectors.toSet());
    }

    // 15. Ordenar usuarios por fecha de registro (más recientes primero).
    public static List<Usuario> ordenarUsuariosPorFecha(List<Usuario> usuarios) {
        return usuarios.stream().sorted(Comparator.comparing(Usuario::getFechaRegistro).reversed()).collect(Collectors.toList());
    }

    // 16. Calcular el pedido más antiguo realizado.
    public static Optional<Pedido> getPedidoMasAntiguo(List<Usuario> usuarios) {
        return usuarios.stream().flatMap(usuario -> usuario.getPedidos().stream()).sorted(Comparator.comparing(Pedido::getFecha)).findFirst();
    }

    // 17. Verificar si algún usuario ha comprado un producto con precio mayor a 1000.
    public static boolean existeCompraMayorA1000(List<Usuario> usuarios) {
        return usuarios.stream().flatMap(usuario -> usuario.getPedidos().stream()).flatMap(pedido -> pedido.getProductos().stream()).anyMatch(producto -> producto.getPrecio()>1000);
    }

    // 18. Obtener los nombres de usuarios separados por coma.
    public static String getNombresSeparadosPorComa(List<Usuario> usuarios) {
        return usuarios.stream().map(Usuario::getNombre).collect(Collectors.joining(","));
    }

    // 19. Contar cuántos productos ha comprado en total el sistema.
    public static long contarProductosTotales(List<Usuario> usuarios) {
        return usuarios.stream().flatMap(usuario -> usuario.getPedidos().stream()).flatMap(pedido -> pedido.getProductos().stream()).count();
    }

    // 20. Listar los pedidos agrupados por usuario.
    public static Map<String, List<Pedido>> agruparPedidosPorUsuario(List<Usuario> usuarios) {
        return usuarios.stream().collect(Collectors.toMap(
                Usuario::getNombre, usuario -> usuario.getPedidos()
        ));
    }

    // 21. Obtener el promedio de precio de productos por categoría.
    public static Map<String, Double> getPromedioPrecioPorCategoria(List<Usuario> usuarios) {
        return usuarios.stream()
                .flatMap(usuario -> usuario.getPedidos().stream())
                .flatMap(pedido -> pedido.getProductos().stream())
                .collect(Collectors.groupingBy(Producto::getCategoria, Collectors.averagingDouble(Producto::getPrecio)));
    }

    // 22. Encontrar el pedido con mayor cantidad de productos.
    public static Optional<Pedido> getPedidoConMasProductos(List<Usuario> usuarios) {
        return usuarios.stream()
                .flatMap(usuario -> usuario.getPedidos().stream())
                .max(Comparator.comparing(pedido -> pedido.getProductos().size()));
    }

    // 23. Crear una lista con los nombres de productos únicos comprados.
    public static List<String> getNombresProductosUnicos(List<Usuario> usuarios) {
        return usuarios.stream()
                .flatMap(usuario -> usuario.getPedidos().stream())
                .flatMap(pedido -> pedido.getProductos().stream())
                .map(Producto::getNombre)
                .distinct()
                .collect(Collectors.toList());
    }

    // 24. Agrupar usuarios por década de edad (20s, 30s, 40s...).
    public static Map<String, List<Usuario>> agruparPorDecada(List<Usuario> usuarios) { return Map.of(); }

    // 25. Obtener el gasto total de todos los pedidos.
    public static double getGastoTotalSistema(List<Usuario> usuarios) {
        return usuarios.stream()
                .flatMap(usuario -> usuario.getPedidos().stream())
                .flatMap(pedido -> pedido.getProductos().stream())
                .mapToDouble(Producto::getPrecio)
                .sum();
    }

    // 26. Listar productos ordenados por precio descendente (únicos).
    public static List<Producto> getProductosOrdenadosPorPrecio(List<Usuario> usuarios) {
        return usuarios.stream()
                .flatMap(usuario -> usuario.getPedidos().stream())
                .flatMap(pedido -> pedido.getProductos().stream())
                .sorted(Comparator.comparing(Producto::getPrecio).reversed())
                .distinct()
                .collect(Collectors.toList());
    }

    // 27. Verificar si todos los usuarios tienen al menos un pedido.
    public static boolean todosTienenPedidos(List<Usuario> usuarios) {
        return usuarios.stream()
                .allMatch(usuario -> usuario.getPedidos().size()>0);
    }

    // 28. Encontrar el usuario con más gasto total.
    public static Optional<Usuario> getUsuarioConMasGasto(List<Usuario> usuarios) {
            return usuarios.stream()
                    .max(Comparator.comparingDouble(usuario ->
                                    usuario.getPedidos().stream()
                                     .flatMap(pedido -> pedido.getProductos().stream())
                                    .mapToDouble(Producto::getPrecio)
                                            .sum()));
         }

    // 29. Generar un mapa categoría -> cantidad de productos comprados.
    public static Map<String, Long> contarProductosPorCategoria(List<Usuario> usuarios) {
        return usuarios.stream()
                .flatMap(usuario -> usuario.getPedidos().stream())
                .flatMap(pedido -> pedido.getProductos().stream())
                .collect(Collectors.groupingBy(
                        Producto::getCategoria, Collectors.counting()
                ));
    }

    // 30. Crear un reporte (String) con el total gastado por cada usuario, ordenado de mayor a menor.
    public static String getReporteGastoPorUsuario(List<Usuario> usuarios) { return ""; }


}
