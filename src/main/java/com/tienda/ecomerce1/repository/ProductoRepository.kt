package com.tienda.ecomerce1.repository

import com.tienda.ecomerce1.model.Producto
import org.springframework.data.jpa.repository.JpaRepository

interface ProductoRepository : JpaRepository<Producto, Integer> {

}