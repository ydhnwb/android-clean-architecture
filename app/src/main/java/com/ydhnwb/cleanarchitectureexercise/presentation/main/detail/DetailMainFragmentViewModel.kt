package com.ydhnwb.cleanarchitectureexercise.presentation.main.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ydhnwb.cleanarchitectureexercise.data.product.remote.dto.ProductUpdateRequest
import com.ydhnwb.cleanarchitectureexercise.domain.common.base.BaseResult
import com.ydhnwb.cleanarchitectureexercise.domain.product.entity.ProductEntity
import com.ydhnwb.cleanarchitectureexercise.domain.product.usecase.GetProductByIdUseCase
import com.ydhnwb.cleanarchitectureexercise.domain.product.usecase.UpdateProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMainFragmentViewModel @Inject constructor(
    private val getProductByIdUseCase: GetProductByIdUseCase,
    private val updateProductUseCase: UpdateProductUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<DetailMainFragmentState>(DetailMainFragmentState.Init)
    val state : StateFlow<DetailMainFragmentState> get() = _state

    private val _product = MutableStateFlow<ProductEntity?>(null)
    val product : StateFlow<ProductEntity?> get() = _product

    private fun setLoading(){
        _state.value = DetailMainFragmentState.IsLoading(true)
    }

    private fun hideLoading(){
        _state.value = DetailMainFragmentState.IsLoading(false)
    }

    private fun showToast(message: String){
        _state.value = DetailMainFragmentState.ShowToast(message)
    }

    fun getProductById(id: String){
        viewModelScope.launch {
            getProductByIdUseCase.invoke(id)
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.stackTraceToString())
                }
                .collect { result ->
                    hideLoading()
                    when(result){
                        is BaseResult.Success -> {
                            _product.value = result.data
                        }
                        is BaseResult.Error -> {
                            showToast(result.rawResponse.message)
                        }
                    }
                }
        }
    }

    fun update(productUpdateRequest: ProductUpdateRequest){
        viewModelScope.launch {
            updateProductUseCase.invoke(productUpdateRequest)
                .onStart {
                    setLoading()
                }
                .catch { exception ->
                    hideLoading()
                    showToast(exception.stackTraceToString())
                }
                .collect { result ->
                    hideLoading()
                    when(result){
                        is BaseResult.Success -> {
                            _state.value = DetailMainFragmentState.SuccessUpdate
                        }
                        is BaseResult.Error -> {
                            showToast(result.rawResponse.message)
                        }
                    }
                }
        }
    }

}

sealed class DetailMainFragmentState {
    object Init : DetailMainFragmentState()
    object SuccessUpdate : DetailMainFragmentState()
    data class IsLoading(val isLoading: Boolean) : DetailMainFragmentState()
    data class ShowToast(val message : String) : DetailMainFragmentState()
}