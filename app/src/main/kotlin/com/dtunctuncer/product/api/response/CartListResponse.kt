package com.dtunctuncer.product.api.response

import com.google.gson.annotations.SerializedName

data class CartListResponse(

	@field:SerializedName("products")
	val products: List<DetailResponse?>? = null
)