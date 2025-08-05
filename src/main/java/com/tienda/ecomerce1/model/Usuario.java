package com.tienda.ecomerce1.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Usuarios")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_usuario;

    private String nombre_usuario;
    private String nombre;
    private String correo;
    private long telefono;
    private String direccion;
    private String contrasena;
    private String tipo;


    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference  // Evita la recursión al serializar la lista de productos
    private List<Producto> productoList;

    @OneToMany(mappedBy = "usuario")
    @JsonManagedReference  // Evita la recursión al serializar la lista de órdenes
    private List<Orden> ordenesList;
}
