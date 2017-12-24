package com.dtunctuncer.product.main

import android.content.Intent
import android.os.Bundle
import android.support.transition.TransitionManager
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Toast
import com.dtunctuncer.product.ProductApp
import com.dtunctuncer.product.R
import com.dtunctuncer.product.base.BaseActivity
import com.dtunctuncer.product.db.entitiy.Product
import com.dtunctuncer.product.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), IMainView, (Product) -> Unit {


    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var adapter: ProductAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        initToolbar()

        presenter.getCartList()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(true)
    }

    private fun initAdapter() {
        adapter = ProductAdapter(this)
        productRecycler.layoutManager = GridLayoutManager(this, 2)
        productRecycler.adapter = adapter
    }

    override fun onInject() {
        DaggerMainComponent.builder()
                .applicationComponent((application as ProductApp).component)
                .mainModule(MainModule())
                .build()
                .inject(this)

        presenter.attachView(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        val searchItem = menu?.findItem(R.id.action_search)
        initSearch(searchItem)

        return super.onCreateOptionsMenu(menu)
    }

    private fun initSearch(searchItem: MenuItem?) {
        val searchView = searchItem?.actionView as SearchView
        searchView.queryHint = getString(R.string.search)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String): Boolean {
                presenter.search(p0)
                return true
            }

            override fun onQueryTextChange(p0: String): Boolean {
                presenter.search(p0)
                return true
            }

        })
    }


    override fun showProducts(productList: List<Product>) {
        adapter.setList(productList)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when (item?.itemId) {
        R.id.action_search -> {
            TransitionManager.beginDelayedTransition(toolbar as ViewGroup)
            item.expandActionView()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun invoke(product: Product) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.ARG_PRODUCT, product)
        startActivity(intent)
    }

}