package com.dtunctuncer.product.main

import com.dtunctuncer.product.api.AmazonApi
import com.dtunctuncer.product.db.AppDatabase
import com.dtunctuncer.product.di.scope.ActivityScope
import dagger.Module
import dagger.Provides


@Module
class MainModule {

    @Provides
    @ActivityScope
    internal fun providePresenter(api: AmazonApi, db: AppDatabase): MainPresenter = MainPresenter(api, db)

}