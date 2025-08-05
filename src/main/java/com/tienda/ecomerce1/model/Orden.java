package com.tienda.ecomerce1.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Ordenes")
@Data  // Genera automáticamente getters, setters, equals, hashCode y toString
@NoArgsConstructor  // Genera el constructor sin argumentos
@AllArgsConstructor  // Genera el constructor con todos los argumentos
public class Orden {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String numero;
    private Date fecha_creacion;
    private Date fecha_recibida;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "orden")
    private List<OrdenDetalle> ordenDetalles;
}
