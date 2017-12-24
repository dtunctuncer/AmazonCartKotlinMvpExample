package com.dtunctuncer.product.di.component

import android.content.Context
import android.content.SharedPreferences
import com.dtunctuncer.product.ProductApp
import com.dtunctuncer.product.api.AmazonApi
import com.dtunctuncer.product.db.AppDatabase
import com.dtunctuncer.product.di.module.ApplicationModule
import com.dtunctuncer.product.di.module.DatabaseModule


import com.dtunctuncer.product.di.module.NetModule

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, NetModule::class, DatabaseModule::class))
interface ApplicationComponent {
    fun app(): ProductApp

    fun context(): Context

    fun preferences(): SharedPreferences

    fun amazonApi(): AmazonApi

    fun db(): AppDatabase
}
