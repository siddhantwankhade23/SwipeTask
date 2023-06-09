package com.example.swipetask.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipetask.data.model.AddProductRequest
import com.example.swipetask.data.model.AddProductResponse
import com.example.swipetask.data.model.Product
import com.example.swipetask.data.repository.ProductRemoteRepository
import com.example.swipetask.utils.NetworkHelper
import com.example.swipetask.utils.NetworkResult
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val productRemoteRepository: ProductRemoteRepository,
    private val networkHelper: NetworkHelper
) :
    ViewModel() {

    private val _productsList = MutableLiveData<List<Product>>()
    val productsList: LiveData<List<Product>> by lazy { _productsList }

    private val _productsAddedResponse = MutableLiveData<AddProductResponse>()
    val productsAddedResponse: LiveData<AddProductResponse> by lazy { _productsAddedResponse }

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> by lazy { _errorMessage }

    fun fetchProducts() {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                when (val networkResult = productRemoteRepository.getProducts()) {
                    is NetworkResult.Success -> _productsList.value = networkResult.data
                    is NetworkResult.Error -> _errorMessage.value = networkResult.message
                    is NetworkResult.Exception -> _errorMessage.value = networkResult.e.message
                }
            } else _errorMessage.postValue("No internet connection")
        }

    }

    fun addProducts(addProductRequest: AddProductRequest) {
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                when (val networkResult = productRemoteRepository.addProduct(addProductRequest)) {
                    is NetworkResult.Success -> _productsAddedResponse.value = networkResult.data
                    is NetworkResult.Error -> _errorMessage.value = networkResult.message
                    is NetworkResult.Exception -> _errorMessage.value = networkResult.e.message
                }
            } else _errorMessage.postValue("No internet connection")

        }
    }
}