package com.dtunctuncer.product

import com.crashlytics.android.Crashlytics
import com.crashlytics.android.core.CrashlyticsCore
import com.dtunctuncer.product.di.component.ApplicationComponent
import com.dtunctuncer.product.di.component.DaggerApplicationComponent
import com.dtunctuncer.product.di.module.ApplicationModule
import com.dtunctuncer.product.utils.timber.CrashReportTree
import com.jakewharton.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import io.fabric.sdk.android.Fabric
import timber.log.Timber


class ProductApp : android.app.Application() {

    val component: ApplicationComponent by lazy { DaggerApplicationComponent.builder().applicationModule(ApplicationModule(this)).build() }

    override fun onCreate() {
        super.onCreate()

        //Picasso
        val builder = Picasso.Builder(this)
        builder.loggingEnabled(BuildConfig.DEBUG)
        builder.indicatorsEnabled(true)
        builder.downloader(OkHttp3Downloader(this, Long.MAX_VALUE))
        Picasso.setSingletonInstance(builder.build())

        //Crashlytics
        val core = CrashlyticsCore.Builder()
                .disabled(BuildConfig.DEBUG)
                .build()

        Fabric.with(this, Crashlytics.Builder().core(core).build())

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportTree())
        }
    }

}

