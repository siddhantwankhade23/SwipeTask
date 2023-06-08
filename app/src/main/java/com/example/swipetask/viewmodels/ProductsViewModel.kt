package com.example.swipetask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipetask.data.model.AddProductRequest
import com.example.swipetask.data.model.AddProductResponse
import com.example.swipetask.data.model.Product
import com.example.swipetask.data.repository.ProductRemoteRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductsViewModel(private val productRemoteRepository: ProductRemoteRepository) : ViewModel() {

    private val _productsList = MutableLiveData<List<Product>>()
    val productsList : LiveData<List<Product>> by lazy { _productsList }

    private val _productsAddedResponse = MutableLiveData<AddProductResponse>()
    val productsAddedResponse : LiveData<AddProductResponse> by lazy { _productsAddedResponse }

    fun fetchProducts(){
        viewModelScope.launch {
            delay(3000)
            val response = productRemoteRepository.getProducts()

            when {
                response.isSuccessful -> {
                    _productsList.value = response.body()
                }

            }

        }
    }

    fun addProducts(addProductRequest: AddProductRequest){
        viewModelScope.launch {
            val response = productRemoteRepository.addProduct(addProductRequest)

            when {
                response.isSuccessful -> {
                    _productsAddedResponse.value = response.body()
                }

            }

        }
    }
}