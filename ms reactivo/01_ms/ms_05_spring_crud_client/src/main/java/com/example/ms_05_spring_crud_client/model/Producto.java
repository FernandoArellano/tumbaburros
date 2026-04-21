package com.example.ms_05_spring_crud_client.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Producto {
    private int codProducto;
    private String nombre;
    private String categoria;
    private double precioUnitario;
    private int stock;
}
