package com.example._3_ms.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Producto {
    private int codProducto;
    private String nombre;
    private String categoria;
    private double precioUnitario;
    private int stock;
}
