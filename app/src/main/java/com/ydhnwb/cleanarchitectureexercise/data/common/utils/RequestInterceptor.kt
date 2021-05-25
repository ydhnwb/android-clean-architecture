package com.ydhnwb.cleanarchitectureexercise.data.common.utils

import com.ydhnwb.cleanarchitectureexercise.infra.utils.SharedPrefs
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class RequestInterceptor constructor(private val pref: SharedPrefs) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = pref.getToken()
        val newRequest = chain.request().newBuilder()
            .addHeader("Authorization", token)
            .build()
        return chain.proceed(newRequest)
    }
}