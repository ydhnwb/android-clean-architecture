package com.ydhnwb.cleanarchitectureexercise.data.product.remote.api

import com.ydhnwb.cleanarchitectureexercise.data.common.utils.WrappedListResponse
import com.ydhnwb.cleanarchitectureexercise.data.common.utils.WrappedResponse
import com.ydhnwb.cleanarchitectureexercise.data.product.remote.dto.ProductResponse
import com.ydhnwb.cleanarchitectureexercise.data.product.remote.dto.ProductUpdateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface ProductApi {
    @GET("product/")
    suspend fun getAllMyProducts() : Response<WrappedListResponse<ProductResponse>>

    @GET("product/{id}")
    suspend fun getProductById(@Path("id") id: String) : Response<WrappedResponse<ProductResponse>>

    @PUT("product/{id}")
    suspend fun updateProduct(@Body productUpdateRequest: ProductUpdateRequest) : Response<WrappedResponse<ProductResponse>>
}