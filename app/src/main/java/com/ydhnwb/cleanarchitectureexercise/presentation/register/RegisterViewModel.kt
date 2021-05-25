package com.ydhnwb.cleanarchitectureexercise.presentation.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ydhnwb.cleanarchitectureexercise.data.common.utils.WrappedResponse
import com.ydhnwb.cleanarchitectureexercise.data.register.remote.dto.RegisterRequest
import com.ydhnwb.cleanarchitectureexercise.data.register.remote.dto.RegisterResponse
import com.ydhnwb.cleanarchitectureexercise.domain.common.base.BaseResult
import com.ydhnwb.cleanarchitectureexercise.domain.register.entity.RegisterEntity
import com.ydhnwb.cleanarchitectureexercise.domain.register.usecase.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val registerUseCase: RegisterUseCase) : ViewModel() {
    private val state = MutableStateFlow<RegisterActivityState>(RegisterActivityState.Init)
    val mState: StateFlow<RegisterActivityState> get() = state

    private fun setLoading(){
        state.value = RegisterActivityState.IsLoading(true)
    }

    private fun hideLoading(){
        state.value = RegisterActivityState.IsLoading(false)
    }

    private fun showToast(message: String){
        state.value  = RegisterActivityState.ShowToast(message)
    }

    private fun successRegister(registerEntity: RegisterEntity){
        state.value = RegisterActivityState.SuccessRegister(registerEntity)
    }

    private fun failedRegister(rawResponse: WrappedResponse<RegisterResponse>){
        state.value = RegisterActivityState.ErrorRegister(rawResponse)
    }

    fun register(registerRequest: RegisterRequest){
        viewModelScope.launch {
            registerUseCase.invoke(registerRequest)
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    showToast(exception.message.toString())
                    hideLoading()
                }
                .collect { result ->
                    hideLoading()
                    when(result){
                        is BaseResult.Success -> successRegister(result.data)
                        is BaseResult.Error -> failedRegister(result.rawResponse)
                    }
                }
        }
    }
}

sealed class RegisterActivityState {
    object Init : RegisterActivityState()
    data class IsLoading(val isLoading: Boolean) : RegisterActivityState()
    data class ShowToast(val message: String) : RegisterActivityState()
    data class SuccessRegister(val registerEntity: RegisterEntity) : RegisterActivityState()
    data class ErrorRegister(val rawResponse: WrappedResponse<RegisterResponse>) : RegisterActivityState()
}