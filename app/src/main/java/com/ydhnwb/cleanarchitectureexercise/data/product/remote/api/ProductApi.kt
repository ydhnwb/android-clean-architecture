package com.ydhnwb.cleanarchitectureexercise.data.product.remote.api

import com.ydhnwb.cleanarchitectureexercise.data.common.utils.WrappedListResponse
import com.ydhnwb.cleanarchitectureexercise.data.product.remote.dto.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface ProductApi {
    @GET("product/")
    suspend fun getAllMyProducts() : Response<WrappedListResponse<ProductResponse>>
}