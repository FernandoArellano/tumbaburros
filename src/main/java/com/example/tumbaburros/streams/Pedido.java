package com.example.tumbaburros.streams;

import java.time.LocalDate;
import java.util.List;

public class Pedido {
    private int id;
    private LocalDate fecha;
    private List<Producto> productos;

    public Pedido(int id, LocalDate fecha, List<Producto> productos) {
        this.id = id;
        this.fecha = fecha;
        this.productos = productos;
    }

    public int getId() { return id; }
    public LocalDate getFecha() { return fecha; }
    public List<Producto> getProductos() { return productos; }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", productos=" + productos +
                '}';
    }
}