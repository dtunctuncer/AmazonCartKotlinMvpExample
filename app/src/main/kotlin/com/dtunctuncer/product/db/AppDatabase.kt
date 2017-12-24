package com.dtunctuncer.product.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.dtunctuncer.product.db.dao.ProductDao
import com.dtunctuncer.product.db.entitiy.Product

@Database(entities = arrayOf(Product::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}