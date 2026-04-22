package com.example._3_ms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {
    private int codProducto;
    private String nombre;
    private int unidades;
    private String direccion;
}
