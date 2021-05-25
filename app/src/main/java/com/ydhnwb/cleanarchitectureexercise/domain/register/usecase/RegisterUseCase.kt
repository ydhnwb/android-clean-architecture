package com.ydhnwb.cleanarchitectureexercise.domain.register.usecase

import com.ydhnwb.cleanarchitectureexercise.data.common.utils.WrappedResponse
import com.ydhnwb.cleanarchitectureexercise.data.register.remote.dto.RegisterRequest
import com.ydhnwb.cleanarchitectureexercise.data.register.remote.dto.RegisterResponse
import com.ydhnwb.cleanarchitectureexercise.domain.common.base.BaseResult
import com.ydhnwb.cleanarchitectureexercise.domain.register.RegisterRepository
import com.ydhnwb.cleanarchitectureexercise.domain.register.entity.RegisterEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RegisterUseCase @Inject constructor(private val registerRepository: RegisterRepository) {
    suspend fun invoke(registerRequest: RegisterRequest) : Flow<BaseResult<RegisterEntity, WrappedResponse<RegisterResponse>>> {
        return registerRepository.register(registerRequest)
    }
}