package com.example._1_ms.service;

import com.example._1_ms.model.Producto;

import java.util.List;

public interface ProductosService {
    List<Producto> catalogo();
    List<Producto> productorsCategoria(String categoria);
    Producto productoCodigo(int cod);
    void altaProducto(Producto producto);
    Producto eliminarProducto(int cod);
    Producto actualizarPrecio(int cod, double precio);

}
