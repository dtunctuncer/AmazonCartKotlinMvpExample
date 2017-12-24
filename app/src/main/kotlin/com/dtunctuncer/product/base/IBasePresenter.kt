package com.dtunctuncer.product.base

interface IBasePresenter<in V : IBaseView> {

    fun attachView(view: V)

    fun detachView()
}