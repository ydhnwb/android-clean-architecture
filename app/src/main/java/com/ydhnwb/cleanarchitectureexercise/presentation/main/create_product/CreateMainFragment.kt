package com.ydhnwb.cleanarchitectureexercise.presentation.main.create_product

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ydhnwb.cleanarchitectureexercise.R
import com.ydhnwb.cleanarchitectureexercise.data.product.remote.dto.ProductCreateRequest
import com.ydhnwb.cleanarchitectureexercise.databinding.FragmentMainCreateBinding
import com.ydhnwb.cleanarchitectureexercise.presentation.common.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class CreateMainFragment : Fragment(R.layout.fragment_main_create){
    private var _binding : FragmentMainCreateBinding? = null
    private val binding get() = _binding!!

    private val viewModel : CreateMainFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainCreateBinding.bind(view)
        observe()
        createProduct()
    }

    private fun setResultOkToPreviousFragment(){
        val r = Bundle().apply {
            putBoolean("success_create", true)
        }
        setFragmentResult("success_create", r)
    }

    private fun observe(){
        viewModel.mState.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleState(state: CreateMainFragmentState){
        when(state){
            is CreateMainFragmentState.IsLoading -> handleLoading(state.isLoading)
            is CreateMainFragmentState.SuccessCreate -> {
                setResultOkToPreviousFragment()
                findNavController().navigateUp()
            }
            is CreateMainFragmentState.ShowToast -> requireActivity().showToast(state.message)
            is CreateMainFragmentState.Init -> Unit
        }
    }

    private fun createProduct(){
        binding.saveButton.setOnClickListener {
            val name = binding.productNameEditText.text.toString().trim()
            val price = binding.productPriceEditText.text.toString().trim()
            if(validate(name, price)){
                viewModel.createProduct(ProductCreateRequest(name, price.toInt()))
            }
        }
    }

    private fun validate(name: String, price: String) : Boolean {
        resetAllError()

        if(name.isEmpty()){
            setProductNameError(getString(R.string.error_product_name_not_valid))
            return false
        }

        if(price.toIntOrNull() == null){
            setProductPriceError(getString(R.string.error_price_not_valid))
            return false
        }

        return true
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.saveButton.isEnabled = !isLoading
    }

    private fun setProductNameError(e: String?){
        binding.productNameInput.error = e
    }

    private fun setProductPriceError(e: String?){
        binding.productPriceInput.error = e
    }

    private fun resetAllError(){
        setProductNameError(null)
        setProductPriceError(null)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}