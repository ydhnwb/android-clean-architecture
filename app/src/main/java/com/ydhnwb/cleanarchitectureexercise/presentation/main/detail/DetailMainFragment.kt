package com.ydhnwb.cleanarchitectureexercise.presentation.main.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ydhnwb.cleanarchitectureexercise.R
import com.ydhnwb.cleanarchitectureexercise.data.product.remote.dto.ProductUpdateRequest
import com.ydhnwb.cleanarchitectureexercise.databinding.FragmentMainDetailBinding
import com.ydhnwb.cleanarchitectureexercise.domain.product.entity.ProductEntity
import com.ydhnwb.cleanarchitectureexercise.presentation.common.extension.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailMainFragment : Fragment(R.layout.fragment_main_detail) {

    private var _binding: FragmentMainDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailMainFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentMainDetailBinding.bind(view)
        observe()
        update()
        delete()
        fetchCurrentProduct()
    }

    private fun fetchCurrentProduct(){
        val id = arguments?.getInt("product_id") ?: 0
        if (id != 0){
            viewModel.getProductById(id.toString())
        }
    }

    private fun observe(){
        observeState()
        observeProduct()
    }

    private fun update(){
        binding.updateButton.setOnClickListener {
            val name = binding.productNameEditText.text.toString().trim()
            val price = binding.productPriceEditText.text.toString().trim()
            val id = arguments?.getInt("product_id") ?: 0
            if(validate(name, price)){
                viewModel.update(ProductUpdateRequest(name, price.toInt()), id.toString())
            }
        }
    }

    private fun delete(){
        binding.deleteButton.setOnClickListener {
            val id = arguments?.getInt("product_id") ?: 0
            viewModel.delete(id.toString())
        }
    }

    private fun observeState(){
        viewModel.state.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { state -> handleState(state) }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun observeProduct(){
        viewModel.product.flowWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED)
            .onEach { product ->
                product?.let { handleProduct(it) }
            }
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleState(state: DetailMainFragmentState){
        when(state){
            is DetailMainFragmentState.SuccessUpdate -> findNavController().navigate(R.id.action_update_to_home)
            is DetailMainFragmentState.SuccessDelete -> findNavController().navigate(R.id.action_update_to_home)
            is DetailMainFragmentState.Init -> Unit
            is DetailMainFragmentState.ShowToast -> requireActivity().showToast(state.message)
            is DetailMainFragmentState.IsLoading -> handleLoading(state.isLoading)
        }
    }

    private fun handleLoading(isLoading: Boolean){
        binding.deleteButton.isEnabled = !isLoading
        binding.updateButton.isEnabled = !isLoading
    }

    private fun handleProduct(productEntity: ProductEntity){
        binding.productNameEditText.setText(productEntity.name)
        binding.productPriceEditText.setText(productEntity.price.toString())
    }

    private fun resetAllError(){
        setProductNameError(null)
        setPriceError(null)
    }

    private fun setProductNameError(e: String?){
        binding.productNameInput.error = e
    }

    private fun setPriceError(e: String?){
        binding.productPriceInput.error = e
    }

    private fun validate(name: String, price: String) : Boolean{
        resetAllError()

        if(name.isEmpty()){
            setProductNameError(getString(R.string.error_product_name_not_valid))
            return false
        }

        val _price = price.toIntOrNull()
        if(_price == null){
            setPriceError(getString(R.string.error_price_not_valid))
            return false
        }

        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}