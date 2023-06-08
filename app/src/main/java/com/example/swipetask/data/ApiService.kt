package com.example.swipetask.data

import com.example.swipetask.data.model.AddProductRequest
import com.example.swipetask.data.model.AddProductResponse
import com.example.swipetask.data.model.Product
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import java.io.File

interface ApiService {

    @POST("/api/public/add")
    @Multipart
    suspend fun addProduct(
        @Part("file") file: File?,
        @Part("price") price: String,
        @Part("product_name") productName: String,
        @Part("product_type") productType: String,
        @Part("tax") tax: String
    ): Response<AddProductResponse>

    @GET("/api/public/get")
    suspend fun getProducts() : Response<List<Product>>
}