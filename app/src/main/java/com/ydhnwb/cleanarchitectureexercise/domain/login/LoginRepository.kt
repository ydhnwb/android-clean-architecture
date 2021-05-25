package com.ydhnwb.cleanarchitectureexercise.domain.login

import com.ydhnwb.cleanarchitectureexercise.data.common.utils.WrappedResponse
import com.ydhnwb.cleanarchitectureexercise.data.login.remote.dto.LoginRequest
import com.ydhnwb.cleanarchitectureexercise.data.login.remote.dto.LoginResponse
import com.ydhnwb.cleanarchitectureexercise.domain.common.base.BaseResult
import com.ydhnwb.cleanarchitectureexercise.domain.login.entity.LoginEntity
import kotlinx.coroutines.flow.Flow

interface LoginRepository {
    suspend fun login(loginRequest: LoginRequest) : Flow<BaseResult<LoginEntity, WrappedResponse<LoginResponse>>>
}