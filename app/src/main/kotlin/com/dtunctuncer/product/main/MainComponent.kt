package com.dtunctuncer.product.main

import com.dtunctuncer.product.di.component.ApplicationComponent
import com.dtunctuncer.product.di.scope.ActivityScope
import dagger.Component


@ActivityScope
@Component(modules = arrayOf(MainModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}