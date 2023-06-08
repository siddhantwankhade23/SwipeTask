package com.example.swipetask.data.repository

import com.example.swipetask.data.ApiService
import com.example.swipetask.data.model.AddProductRequest
import com.example.swipetask.data.model.AddProductResponse
import com.example.swipetask.data.model.Product
import retrofit2.Response

class ProductRemoteRepositoryImpl(private val apiService: ApiService) : ProductRemoteRepository{

    override suspend fun addProduct(addProductRequest: AddProductRequest) : Response<AddProductResponse> {

        return apiService.addProduct(
                addProductRequest.imageFile,
                addProductRequest.price,
                addProductRequest.productName,
                addProductRequest.productType,
                addProductRequest.tax
            )

    }

    override suspend fun getProducts() : Response<List<Product>> {
       return apiService.getProducts()
    }
}