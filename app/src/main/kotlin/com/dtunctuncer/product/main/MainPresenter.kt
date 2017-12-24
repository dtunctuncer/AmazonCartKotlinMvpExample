package com.dtunctuncer.product.main

import com.dtunctuncer.product.api.AmazonApi
import com.dtunctuncer.product.base.BasePresenter
import com.dtunctuncer.product.db.AppDatabase
import com.dtunctuncer.product.db.entitiy.Product
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject


class MainPresenter @Inject constructor(val api: AmazonApi, val db: AppDatabase) : BasePresenter<IMainView>() {

    private var carts = emptyList<Product>()

    fun getCartList() {
        val cartList = api.getCartList()
                .subscribeOn(Schedulers.computation())
                .map {
                    var list = emptyList<Product>()
                    it.products?.forEach {
                        val product = Product(it?.productId!!, it.image!!, it.price!!, it.name!!, it.description)
                        db.productDao().insertProduct(product)
                        list += product
                    }
                    carts = list
                    list
                }

        Flowable.merge(db.productDao().getProductList(), cartList.toFlowable())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.showProducts(it.sortedBy { it.name }) }, { Timber.e(it);view?.onError() })
    }

    fun search(query: String) {
        if (query.isEmpty()) {
            view?.showProducts(carts)
            return
        }
        view?.showProducts(carts.asSequence().filter { it.name.toLowerCase().contains(query.toLowerCase()) }.toList())
    }
}