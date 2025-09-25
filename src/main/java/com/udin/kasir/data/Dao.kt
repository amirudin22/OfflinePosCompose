package com.udin.kasir.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) suspend fun insert(product: Product): Long
    @Query("SELECT * FROM products WHERE sku = :sku LIMIT 1") suspend fun findBySku(sku: String): Product?
    @Query("SELECT * FROM products ORDER BY name") fun allProducts(): Flow<List<Product>>
    @Update suspend fun update(product: Product)
    @Delete suspend fun delete(product: Product)
}
