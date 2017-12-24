package com.dtunctuncer.product

import com.dtunctuncer.product.api.AmazonApi
import com.dtunctuncer.product.api.response.CartListResponse
import com.dtunctuncer.product.db.AppDatabase
import com.dtunctuncer.product.db.dao.ProductDao
import com.dtunctuncer.product.db.entitiy.Product
import com.dtunctuncer.product.main.IMainView
import com.dtunctuncer.product.main.MainPresenter
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import java.io.IOException


class MainPresenterTest {
    private val view: IMainView = mock()
    private val api: AmazonApi = mock()
    private var db: AppDatabase = mock()
    private lateinit var productDao: ProductDao
    private lateinit var presenter: MainPresenter

    @Before
    fun setup() {
        presenter = MainPresenter(api, db)
        presenter.attachView(view)

        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    @Throws(IOException::class)
    fun tearDown() {
        db.close()
    }


    @Test
    fun test_getCartList_should_callSuccess() {
        val mockApiResponse: CartListResponse = mock()
        val mockedResponse: List<Product> = mock()
        val mockedCall: Single<CartListResponse> = Single.just<CartListResponse>(mockApiResponse)

        Mockito.`when`(api.getCartList()).thenReturn(mockedCall)
        presenter.getCartList()
        Mockito.verify(view).showProducts(mockedResponse)

    }

    @Test
    fun test_getCartList_should_callError() {
        val mockedCall: Single<CartListResponse> = Single.error<CartListResponse>(Exception())

        Mockito.`when`(api.getCartList()).thenReturn(mockedCall)
        presenter.getCartList()
        Mockito.verify(view).onError()

    }
}