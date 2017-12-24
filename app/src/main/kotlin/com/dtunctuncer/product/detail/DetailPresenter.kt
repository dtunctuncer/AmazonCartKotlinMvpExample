package com.dtunctuncer.product.detail

import com.dtunctuncer.product.api.AmazonApi
import com.dtunctuncer.product.base.BasePresenter
import com.dtunctuncer.product.db.AppDatabase
import com.dtunctuncer.product.db.entitiy.Product
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class DetailPresenter @Inject constructor(val api: AmazonApi, val db: AppDatabase) : BasePresenter<IDetailView>() {
    fun getProduct(id: String) {
        val apiCall = api.getProductDetail(id)
                .subscribeOn(Schedulers.computation())
                .map {
                    val product = Product(it.productId!!, it.image!!, it.price!!, it.name!!,it.description)
                    db.productDao().insertProduct(product)
                    product
                }


        Flowable.merge(db.productDao().getProduct(id), apiCall.toFlowable())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.showProduct(it) }, { Timber.e(it);view?.onError() })

    }
}