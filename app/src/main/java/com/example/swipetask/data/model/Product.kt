package com.example.swipetask.data.model

import com.google.gson.annotations.SerializedName
import java.io.File

data class Product(

    @SerializedName("image")
    var image: String? = null,
    @SerializedName("price")
    var price: Double? = null,
    @SerializedName("product_name")
    var productName: String? = null,
    @SerializedName("product_type")
    var productType: String? = null,
    @SerializedName("tax")
    var tax: Double? = null
)

data class AddProductRequest(

    @SerializedName("imageFile")
    var imageFile: File?,
    @SerializedName("price")
    var price: String,
    @SerializedName("product_name")
    var productName: String,
    @SerializedName("product_type")
    var productType: String,
    @SerializedName("tax")
    var tax: String

)

data class AddProductResponse(

    @SerializedName("message")
    var message: String,
    @SerializedName("product_details")
    var productDetails: Product? = null,
    @SerializedName("product_id")
    var productId: Int? = null,
    @SerializedName("success")
    var success: Boolean

)