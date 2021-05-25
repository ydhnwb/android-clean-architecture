package com.ydhnwb.cleanarchitectureexercise.domain.register

import com.ydhnwb.cleanarchitectureexercise.data.common.utils.WrappedResponse
import com.ydhnwb.cleanarchitectureexercise.data.register.remote.dto.RegisterRequest
import com.ydhnwb.cleanarchitectureexercise.data.register.remote.dto.RegisterResponse
import com.ydhnwb.cleanarchitectureexercise.domain.common.base.BaseResult
import com.ydhnwb.cleanarchitectureexercise.domain.register.entity.RegisterEntity
import kotlinx.coroutines.flow.Flow

interface RegisterRepository {
    suspend fun register(registerRequest: RegisterRequest) : Flow<BaseResult<RegisterEntity, WrappedResponse<RegisterResponse>>>
}