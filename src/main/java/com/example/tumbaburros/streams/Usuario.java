package com.example.tumbaburros.streams;

import java.time.LocalDate;
import java.util.List;

public class Usuario {
    private int id;
    private String nombre;
    private int edad;
    private String email;
    private LocalDate fechaRegistro;
    private List<Pedido> pedidos;

    public Usuario(int id, String nombre, int edad, String email, LocalDate fechaRegistro, List<Pedido> pedidos) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
        this.fechaRegistro = fechaRegistro;
        this.pedidos = pedidos;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public int getEdad() { return edad; }
    public String getEmail() { return email; }
    public LocalDate getFechaRegistro() { return fechaRegistro; }
    public List<Pedido> getPedidos() { return pedidos; }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", email='" + email + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", pedidos=" + pedidos +
                '}';
    }
}