package com.example.swipetask.data.repository

import com.example.swipetask.data.model.AddProductRequest
import com.example.swipetask.data.model.AddProductResponse
import com.example.swipetask.data.model.Product
import retrofit2.Response

interface ProductRemoteRepository {

    suspend fun addProduct(addProductRequest: AddProductRequest) : Response<AddProductResponse>

    suspend fun getProducts() : Response<List<Product>>
}


