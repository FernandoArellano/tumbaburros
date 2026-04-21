package com.example._3_ms.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table("productos")
public class Producto implements Persistable<Integer>{
    @Id
    @Column("codProducto")
    private Integer codProducto;
    private String nombre;
    private String categoria;
    @Column("precioUnitario")
    private double precioUnitario;
    private int stock;

    @Transient
    private boolean nuevo;
    @Override
    public @Nullable Integer getId() {
        return codProducto;
    }

    @Override
    public boolean isNew() {
        return nuevo;
    }
}
