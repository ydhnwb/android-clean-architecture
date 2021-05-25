package com.ydhnwb.cleanarchitectureexercise.domain.product.usecase

import com.ydhnwb.cleanarchitectureexercise.data.common.utils.WrappedListResponse
import com.ydhnwb.cleanarchitectureexercise.data.product.remote.dto.ProductResponse
import com.ydhnwb.cleanarchitectureexercise.domain.common.base.BaseResult
import com.ydhnwb.cleanarchitectureexercise.domain.product.ProductRepository
import com.ydhnwb.cleanarchitectureexercise.domain.product.entity.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMyProductUseCase @Inject constructor(private val productRepository: ProductRepository) {
    suspend fun invoke() : Flow<BaseResult<List<ProductEntity>, WrappedListResponse<ProductResponse>>> {
        return productRepository.getAllMyProducts()
    }
}