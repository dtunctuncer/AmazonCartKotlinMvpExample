package com.dtunctuncer.product.main

import com.dtunctuncer.product.base.IBaseView
import com.dtunctuncer.product.db.entitiy.Product

interface IMainView : IBaseView {
    fun showProducts(productList: List<Product>)
}