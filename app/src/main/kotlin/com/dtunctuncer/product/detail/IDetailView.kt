package com.dtunctuncer.product.detail

import com.dtunctuncer.product.base.IBaseView
import com.dtunctuncer.product.db.entitiy.Product

interface IDetailView : IBaseView {
    fun showProduct(product: Product)
}