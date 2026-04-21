package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "productos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Producto {

    @Id
    private int id;
    private String nombre;
    private String categoria;
    private double precioUnitario;
    private int stock;
}
