package com.udin.kasir.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val username: String,
    val passwordHash: String,
    val role: String // "OWNER" or "KASIR"
)

@Entity(tableName = "categories")
data class Category(@PrimaryKey(autoGenerate = true) val id: Long = 0, val name: String)

@Entity(tableName = "products")
data class Product(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val sku: String,
    val name: String,
    val categoryId: Long?,
    val price: Double,
    val stock: Int,
    val taxPercent: Double = 0.0,
    val discountPercent: Double = 0.0
)
