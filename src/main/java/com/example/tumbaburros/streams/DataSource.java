package com.example.tumbaburros.streams;

import java.time.LocalDate;
import java.util.List;

public class DataSource {
    public static List<Usuario> getUsuarios() {
        List<Producto> productos1 = List.of(
                new Producto(1, "Laptop", "Tecnología", 1200),
                new Producto(2, "Mouse", "Accesorios", 25)
        );
        List<Producto> productos2 = List.of(
                new Producto(3, "Silla", "Muebles", 150),
                new Producto(4, "Escritorio", "Muebles", 300)
        );
        List<Producto> productos3 = List.of(
                new Producto(5, "Teléfono", "Tecnología", 800),
                new Producto(6, "Audífonos", "Accesorios", 50)
        );

        Pedido pedido1 = new Pedido(1, LocalDate.of(2024, 1, 15), productos1);
        Pedido pedido2 = new Pedido(2, LocalDate.of(2024, 2, 20), productos2);
        Pedido pedido3 = new Pedido(3, LocalDate.of(2024, 3, 10), productos3);

        return List.of(
                new Usuario(1, "Carlos", 25, "carlos@mail.com", LocalDate.of(2023,5,1), List.of(pedido1, pedido2)),
                new Usuario(2, "Ana", 32, "ana@mail.com", LocalDate.of(2022,3,15), List.of(pedido3)),
                new Usuario(3, "Luis", 40, "luis@mail.com", LocalDate.of(2021,8,10), List.of(pedido2, pedido3)),
                new Usuario(4, "Sofía", 29, "sofia@mail.com", LocalDate.of(2024,2,25), List.of(pedido1))
        );
    }
}