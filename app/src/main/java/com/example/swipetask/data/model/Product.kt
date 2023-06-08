package com.example.swipetask.data.model

import com.google.gson.annotations.SerializedName

data class Product(

    @SerializedName("image")
    var image: String? = null,
    @SerializedName("price")
    var price: Int? = null,
    @SerializedName("product_name")
    var productName: String? = null,
    @SerializedName("product_type")
    var productType: String? = null,
    @SerializedName("tax")
    var tax: Int? = null
)