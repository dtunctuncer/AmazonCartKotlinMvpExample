package com.dtunctuncer.product.base


interface IBaseView {
    fun onError()
    fun setPresenter(presenter: BasePresenter<*>)
}