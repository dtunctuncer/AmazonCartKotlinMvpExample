package com.dtunctuncer.product.api.response

import com.google.gson.annotations.SerializedName

data class DetailResponse(

        @field:SerializedName("image")
        val image: String? = null,

        @field:SerializedName("price")
        val price: Int? = null,

        @field:SerializedName("product_id")
        val productId: String? = null,

        @field:SerializedName("name")
        val name: String? = null,

        @field:SerializedName("description")
        val description: String? = null
)