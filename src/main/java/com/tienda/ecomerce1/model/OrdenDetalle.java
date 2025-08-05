package com.tienda.ecomerce1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "Detalles")
@Data  // Genera automáticamente getters, setters, equals, hashCode y toString
@NoArgsConstructor  // Genera el constructor sin argumentos
@AllArgsConstructor  // Genera el constructor con todos los argumentos
public class OrdenDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_detalle;

    private String nombre;
    private double precio;
    private double total;

    @ManyToOne
    @JoinColumn(name = "orden_id")
    private Orden orden;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;
}
