package com.example._7_app_cliente_rastreador.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Elemento {
    private String nombre;
    private String categoria;
    private String tienda;
    private double precioUnitario;
}
