package com.ydhnwb.cleanarchitectureexercise.data.login.remote.api

import com.ydhnwb.cleanarchitectureexercise.data.common.utils.WrappedResponse
import com.ydhnwb.cleanarchitectureexercise.data.login.remote.dto.LoginRequest
import com.ydhnwb.cleanarchitectureexercise.data.login.remote.dto.LoginResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("auth/login")
    suspend fun login(@Body loginRequest: LoginRequest) : Response<WrappedResponse<LoginResponse>>
}