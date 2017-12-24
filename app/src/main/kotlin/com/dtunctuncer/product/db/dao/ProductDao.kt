package com.dtunctuncer.product.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.dtunctuncer.product.db.entitiy.Product
import io.reactivex.Flowable

@Dao
interface ProductDao {
    @Query("SELECT * FROM Product")
    fun getProductList(): Flowable<List<Product>>

    @Query("SELECT * FROM Product WHERE id = :productId")
    fun getProduct(productId: String): Flowable<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProduct(product: Product)
}