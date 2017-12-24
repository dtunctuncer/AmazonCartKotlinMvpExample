package com.dtunctuncer.product.di.module

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.dtunctuncer.product.ProductApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(var app: ProductApp) {


    @Provides
    @Singleton
    fun provideApp(): ProductApp = app

    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

    @Provides
    @Singleton
    fun provideSharedPreferences(): SharedPreferences = PreferenceManager.getDefaultSharedPreferences(app)
}
