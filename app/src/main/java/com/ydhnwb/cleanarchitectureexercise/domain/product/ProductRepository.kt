package com.ydhnwb.cleanarchitectureexercise.domain.product

import com.ydhnwb.cleanarchitectureexercise.data.common.utils.WrappedListResponse
import com.ydhnwb.cleanarchitectureexercise.data.common.utils.WrappedResponse
import com.ydhnwb.cleanarchitectureexercise.data.product.remote.dto.ProductResponse
import com.ydhnwb.cleanarchitectureexercise.data.product.remote.dto.ProductUpdateRequest
import com.ydhnwb.cleanarchitectureexercise.domain.common.base.BaseResult
import com.ydhnwb.cleanarchitectureexercise.domain.product.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getAllMyProducts() : Flow<BaseResult<List<ProductEntity>, WrappedListResponse<ProductResponse>>>
    suspend fun getProductById(id: String) : Flow<BaseResult<ProductEntity, WrappedResponse<ProductResponse>>>
    suspend fun updateProduct(productUpdateRequest: ProductUpdateRequest) : Flow<BaseResult<ProductEntity, WrappedResponse<ProductResponse>>>
}