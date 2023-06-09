package com.example.swipetask.data.repository

import com.example.swipetask.data.ApiService
import com.example.swipetask.data.model.AddProductRequest
import com.example.swipetask.data.model.AddProductResponse
import com.example.swipetask.data.model.Product
import com.example.swipetask.utils.NetworkResult
import com.example.swipetask.utils.handleAPIResult
import retrofit2.Response

class ProductRemoteRepositoryImpl(private val apiService: ApiService) : ProductRemoteRepository {

    override suspend fun addProduct(addProductRequest: AddProductRequest): NetworkResult<AddProductResponse> {

        return handleAPIResult {
            apiService.addProduct(
                addProductRequest.imageFile,
                addProductRequest.price,
                addProductRequest.productName,
                addProductRequest.productType,
                addProductRequest.tax
            )
        }


    }

    override suspend fun getProducts(): NetworkResult<List<Product>> {
        return handleAPIResult { apiService.getProducts() }
    }
}