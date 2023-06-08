package com.example.swipetask.screens.addproducts

import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.example.swipetask.R
import com.example.swipetask.data.model.AddProductRequest
import com.example.swipetask.screens.common.viewsmvc.BaseViewMvc
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import java.io.File

class AddProductViewMvx(
    inflater: LayoutInflater,
    parent: ViewGroup?
) : BaseViewMvc<AddProductViewMvx.Listener>(
    inflater,
    parent,
    R.layout.dialog_add_product
) {

    interface Listener {

        fun onAddProductClicked(addProductRequest: AddProductRequest)
        fun onCancelClicked()
        fun captureImage(onImageSelected: (uri: Uri) -> Unit)
    }

    private var ivImage: ImageView
    private val etName: TextInputEditText
    private val etType: MaterialAutoCompleteTextView
    private val etSellingPrice: TextInputEditText
    private val etTaxRate: TextInputEditText
    private var capturedImage: File? = null

    init {
        etName = findViewById(R.id.et_name)
        etType = findViewById(R.id.et_type)
        etSellingPrice = findViewById(R.id.et_price)
        etTaxRate = findViewById(R.id.etTaxRate)
        ivImage = findViewById(R.id.ivImage)

        val productType = arrayOf("Product","Service")
        val arrayAdapter = ArrayAdapter(rootView.context,R.layout.layout_dropdown_menu,productType)
        etType.setAdapter(arrayAdapter)

        ivImage.setOnClickListener {
            for (listener in listeners) {
                listener.captureImage { uri ->
                    uri.let {
                        if (it.path != null) {
                            capturedImage = File(it.path)
                        }
                        ivImage.setImageURI(it)
                    }

                }
            }
        }

        findViewById<MaterialButton>(R.id.cancel_button).setOnClickListener {
            for (listener in listeners) {
                listener.onCancelClicked()
            }
        }

        findViewById<MaterialButton>(R.id.add_button).setOnClickListener {

            if (isAllFieldsValid()) {
                val product = getProduct()

                for (listener in listeners) {
                    listener.onAddProductClicked(product)
                }
            }
        }

    }

    private fun getProduct(): AddProductRequest {

        return AddProductRequest(
            capturedImage,
            etSellingPrice.text.toString(),
            etName.text.toString(),
            etType.text.toString(),
            etTaxRate.text.toString()
        )
    }

    private fun isAllFieldsValid(): Boolean {
        if (etName.text.toString().isBlank()) {
            etName.error = context.getString(R.string.please_enter_product_name)
            return false
        }
        if (etType.text.toString().isBlank()) {
            etType.error = context.getString(R.string.please_enter_product_type)
            return false
        }
        if (etSellingPrice.text.toString().isBlank()) {
            etSellingPrice.error = context.getString(R.string.please_enter_product_price)
            return false
        }
        if (etTaxRate.text.toString().isBlank()) {
            etTaxRate.error = context.getString(R.string.please_enter_product_tax_rate)
            return false
        }

        return true
    }
}