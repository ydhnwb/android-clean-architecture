package com.ydhnwb.cleanarchitectureexercise.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ydhnwb.cleanarchitectureexercise.data.common.utils.WrappedResponse
import com.ydhnwb.cleanarchitectureexercise.data.login.remote.dto.LoginRequest
import com.ydhnwb.cleanarchitectureexercise.data.login.remote.dto.LoginResponse
import com.ydhnwb.cleanarchitectureexercise.domain.common.base.BaseResult
import com.ydhnwb.cleanarchitectureexercise.domain.login.entity.LoginEntity
import com.ydhnwb.cleanarchitectureexercise.domain.login.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase): ViewModel() {
    private val state = MutableStateFlow<LoginActivityState>(LoginActivityState.Init)
    val mState: StateFlow<LoginActivityState> get() = state


    private fun setLoading(){
        state.value = LoginActivityState.IsLoading(true)
    }

    private fun hideLoading(){
        state.value = LoginActivityState.IsLoading(false)
    }

    private fun showToast(message: String){
        state.value = LoginActivityState.ShowToast(message)
    }


    fun login(loginRequest: LoginRequest){
        viewModelScope.launch {
            loginUseCase.execute(loginRequest)
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.message.toString())
                }
                .collect { baseResult ->
                    hideLoading()
                    when(baseResult){
                        is BaseResult.Error -> state.value = LoginActivityState.ErrorLogin(baseResult.rawResponse)
                        is BaseResult.Success -> state.value = LoginActivityState.SuccessLogin(baseResult.data)
                    }
                }
        }
    }



}

sealed class LoginActivityState  {
    object Init : LoginActivityState()
    data class IsLoading(val isLoading: Boolean) : LoginActivityState()
    data class ShowToast(val message: String) : LoginActivityState()
    data class SuccessLogin(val loginEntity: LoginEntity) : LoginActivityState()
    data class ErrorLogin(val rawResponse: WrappedResponse<LoginResponse>) : LoginActivityState()
}