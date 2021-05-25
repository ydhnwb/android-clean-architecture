package com.ydhnwb.cleanarchitectureexercise.domain.login.usecase

import com.ydhnwb.cleanarchitectureexercise.data.common.utils.WrappedResponse
import com.ydhnwb.cleanarchitectureexercise.data.login.remote.dto.LoginRequest
import com.ydhnwb.cleanarchitectureexercise.data.login.remote.dto.LoginResponse
import com.ydhnwb.cleanarchitectureexercise.domain.common.base.BaseResult
import com.ydhnwb.cleanarchitectureexercise.domain.login.LoginRepository
import com.ydhnwb.cleanarchitectureexercise.domain.login.entity.LoginEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository) {
    suspend fun execute(loginRequest: LoginRequest): Flow<BaseResult<LoginEntity, WrappedResponse<LoginResponse>>> {
        return loginRepository.login(loginRequest)
    }

}