package com.example.swipetask.data.repository

import com.example.swipetask.data.model.Product
import retrofit2.Response

interface ProductRemoteRepository {

    suspend fun addProduct()

    suspend fun getProducts() : Response<List<Product>>
}


