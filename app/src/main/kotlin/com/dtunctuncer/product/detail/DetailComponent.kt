package com.dtunctuncer.product.detail

import com.dtunctuncer.product.di.component.ApplicationComponent
import com.dtunctuncer.product.di.scope.ActivityScope
import dagger.Component


@ActivityScope
@Component(modules = arrayOf(DetailModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface DetailComponent {
    fun inject(detailActivity: DetailActivity)
}