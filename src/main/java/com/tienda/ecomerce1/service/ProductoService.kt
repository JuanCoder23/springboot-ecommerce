package com.tienda.ecomerce1.service

import com.tienda.ecomerce1.model.Producto
import java.util.*

interface ProductoService {
    fun save(producto: Producto): Producto
    fun get(id: Int): Optional<Producto>
    fun update(producto: Producto)
    fun delete(id: Int)
    public fun listAll(): List<Producto>


}