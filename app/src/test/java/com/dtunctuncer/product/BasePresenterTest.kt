package com.dtunctuncer.product

import com.dtunctuncer.product.base.BasePresenter
import com.dtunctuncer.product.base.IBaseView
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test

class BasePresenterTest {

    private lateinit var basePresenter: BasePresenter<IBaseView>
    private val view: IBaseView = mock()

    @Before
    fun setUp() {
        basePresenter = BasePresenter()
    }

    @Test
    fun attachView() {
        basePresenter.attachView(view)

        verify(view).setPresenter(basePresenter)
    }

}