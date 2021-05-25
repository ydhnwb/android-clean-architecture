package com.ydhnwb.cleanarchitectureexercise.domain.product

import com.ydhnwb.cleanarchitectureexercise.data.common.utils.WrappedListResponse
import com.ydhnwb.cleanarchitectureexercise.data.product.remote.dto.ProductResponse
import com.ydhnwb.cleanarchitectureexercise.domain.common.base.BaseResult
import com.ydhnwb.cleanarchitectureexercise.domain.product.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    suspend fun getAllMyProducts() : Flow<BaseResult<List<ProductEntity>, WrappedListResponse<ProductResponse>>>
}