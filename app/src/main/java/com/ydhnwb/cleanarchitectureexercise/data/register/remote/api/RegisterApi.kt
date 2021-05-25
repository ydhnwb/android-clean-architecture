package com.ydhnwb.cleanarchitectureexercise.data.register.remote.api

import com.ydhnwb.cleanarchitectureexercise.data.common.utils.WrappedResponse
import com.ydhnwb.cleanarchitectureexercise.data.register.remote.dto.RegisterRequest
import com.ydhnwb.cleanarchitectureexercise.data.register.remote.dto.RegisterResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterApi {
    @POST("auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest) : Response<WrappedResponse<RegisterResponse>>
}