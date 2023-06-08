package com.example.swipetask.data

import com.example.swipetask.data.model.Product
import retrofit2.Response
import retrofit2.http.POST

interface ApiService {

    @POST("/api/public/add")
    suspend fun addProduct()

    @POST("/api/public/get")
    suspend fun getProducts() : Response<List<Product>>
}