package com.dtunctuncer.product.api

import com.dtunctuncer.product.api.response.CartListResponse
import com.dtunctuncer.product.api.response.DetailResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface AmazonApi {

    @GET("cart/list")
    fun getCartList(): Single<CartListResponse>

    @GET("cart/{product-id}/detail")
    fun getProductDetail(@Path("product-id") id: String): Single<DetailResponse>
}