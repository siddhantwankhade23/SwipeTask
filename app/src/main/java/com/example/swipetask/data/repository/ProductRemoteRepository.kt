package com.example.swipetask.data.repository

import com.example.swipetask.data.model.AddProductRequest
import com.example.swipetask.data.model.AddProductResponse
import com.example.swipetask.data.model.Product
import com.example.swipetask.utils.NetworkResult
import retrofit2.Response

interface ProductRemoteRepository {

    suspend fun addProduct(addProductRequest: AddProductRequest) : NetworkResult<AddProductResponse>

    suspend fun getProducts() : NetworkResult<List<Product>>
}


