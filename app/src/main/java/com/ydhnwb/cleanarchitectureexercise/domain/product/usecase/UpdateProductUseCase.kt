package com.ydhnwb.cleanarchitectureexercise.domain.product.usecase

import com.ydhnwb.cleanarchitectureexercise.data.common.utils.WrappedResponse
import com.ydhnwb.cleanarchitectureexercise.data.product.remote.dto.ProductResponse
import com.ydhnwb.cleanarchitectureexercise.data.product.remote.dto.ProductUpdateRequest
import com.ydhnwb.cleanarchitectureexercise.domain.common.base.BaseResult
import com.ydhnwb.cleanarchitectureexercise.domain.product.ProductRepository
import com.ydhnwb.cleanarchitectureexercise.domain.product.entity.ProductEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(private val productRepository: ProductRepository){
    suspend fun invoke(productUpdateRequest: ProductUpdateRequest) : Flow<BaseResult<ProductEntity, WrappedResponse<ProductResponse>>> {
        return productRepository.updateProduct(productUpdateRequest)
    }
}