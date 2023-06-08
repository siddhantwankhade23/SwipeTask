package com.example.swipetask.data.repository

import com.example.swipetask.data.ApiService
import com.example.swipetask.data.model.Product
import retrofit2.Response

class ProductRemoteRepositoryImpl(private val apiService: ApiService) : ProductRemoteRepository{

    override suspend fun addProduct() {
        apiService.addProduct()
    }

    override suspend fun getProducts() : Response<List<Product>> {
       return apiService.getProducts()
    }
}