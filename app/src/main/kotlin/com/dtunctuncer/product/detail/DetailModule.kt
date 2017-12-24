package com.dtunctuncer.product.detail

import com.dtunctuncer.product.api.AmazonApi
import com.dtunctuncer.product.db.AppDatabase
import com.dtunctuncer.product.di.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class DetailModule {
    @Provides
    @ActivityScope
    fun providePresenter(api: AmazonApi, db: AppDatabase) = DetailPresenter(api, db)
}