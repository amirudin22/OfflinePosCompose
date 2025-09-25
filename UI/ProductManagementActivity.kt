// ProductRepository.kt
package com.udin.kasir.data.repository

import android.content.ContentValues
import android.content.Context
import com.udin.kasir.data.database.DatabaseHelper
import com.udin.kasir.data.database.DatabaseHelper.Companion.TABLE_PRODUCT

data class Product(
    val id: Int? = null,
    val name: String,
    val category: String?,
    val price: Double,
    val stock: Int
)

class ProductRepository(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    fun addProduct(product: Product): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("name", product.name)
            put("category", product.category)
            put("price", product.price)
            put("stock", product.stock)
        }
        return db.insert(TABLE_PRODUCT, null, values)
    }

    // Fungsi CRUD lain bisa ditambah (update, delete, getAll)
}
