package com.dtunctuncer.product.detail

import android.os.Bundle
import com.dtunctuncer.product.ProductApp
import com.dtunctuncer.product.R
import com.dtunctuncer.product.base.BaseActivity
import com.dtunctuncer.product.db.entitiy.Product
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : BaseActivity(), IDetailView {


    companion object {
        val ARG_PRODUCT = "product"
    }

    @Inject
    lateinit var presenter: DetailPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolbar()
        parseIntent()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun parseIntent() {
        val product = intent.getParcelableExtra<Product>(ARG_PRODUCT)
        showProduct(product)
        presenter.getProduct(product.id)
    }


    override fun showProduct(product: Product) {
        detailText.text = product.description
        toolbar.title = product.name
        supportActionBar?.title = product.name
        Picasso.with(this).load(product.image).into(image)
    }


    override fun onInject() {
        DaggerDetailComponent.builder()
                .applicationComponent((application as ProductApp).component)
                .detailModule(DetailModule())
                .build()
                .inject(this)

        presenter.attachView(this)
    }
}
