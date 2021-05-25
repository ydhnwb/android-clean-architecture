package com.ydhnwb.cleanarchitectureexercise.data.product.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("id") var id: Int,
    @SerializedName("product_name") var name: String,
    @SerializedName("price") var price: Int,
    @SerializedName("user") var user: ProductUserResponse
)