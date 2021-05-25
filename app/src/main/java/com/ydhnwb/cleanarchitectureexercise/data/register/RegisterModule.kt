package com.ydhnwb.cleanarchitectureexercise.data.register

import com.ydhnwb.cleanarchitectureexercise.data.common.module.NetworkModule
import com.ydhnwb.cleanarchitectureexercise.data.register.remote.api.RegisterApi
import com.ydhnwb.cleanarchitectureexercise.data.register.repository.RegisterRepositoryImpl
import com.ydhnwb.cleanarchitectureexercise.domain.register.RegisterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class RegisterModule {
    @Singleton
    @Provides
    fun provideRegisterApi(retrofit: Retrofit) : RegisterApi {
        return retrofit.create(RegisterApi::class.java)
    }

    @Singleton
    @Provides
    fun provideRegisterRepository(registerApi: RegisterApi) : RegisterRepository {
        return RegisterRepositoryImpl(registerApi)
    }
}