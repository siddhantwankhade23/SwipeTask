package com.example.swipetask.screens.addproducts

import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.example.swipetask.data.model.AddProductRequest
import com.example.swipetask.viewmodels.ProductsViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.activityViewModel

class AddProductDialog : DialogFragment(), AddProductViewMvx.Listener {

    private lateinit var startForImageResult: ActivityResultLauncher<Intent>
    private val productViewModel: ProductsViewModel by activityViewModel()
    private lateinit var viewMvx: AddProductViewMvx
    private lateinit var onImageSelected: (uri: Uri) -> Unit

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val dialog = Dialog(requireContext())

        viewMvx = AddProductViewMvx(layoutInflater, null)
        dialog.setContentView(viewMvx.rootView)

        startForImageResult =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
                val resultCode = result.resultCode
                val data = result.data

                if (resultCode == Activity.RESULT_OK) {
                    //Image Uri will not be null for RESULT_OK
                    val fileUri = data?.data!!

                    onImageSelected(fileUri)
                }
            }

        return dialog
    }

    override fun onStart() {
        super.onStart()
        viewMvx.registerListener(this)

        productViewModel.errorMessage.observe(this) {
            viewMvx.hideProgressIndication()
            Toast.makeText(context, it, Toast.LENGTH_LONG).show()
        }
    }

    override fun onStop() {
        super.onStop()
        viewMvx.unregisterListener(this)
    }

    override fun onAddProductClicked(addProductRequest: AddProductRequest) {
        productViewModel.productsAddedResponse.observe(this) {
            Toast.makeText(viewMvx.rootView.context, it.message, Toast.LENGTH_LONG).show()
            viewMvx.hideProgressIndication()
            if (it.success) {
                dismiss()
            }
        }

        viewMvx.showProgressIndication()
        productViewModel.addProducts(addProductRequest)
    }

    override fun onCancelClicked() {
        dismiss()
    }

    override fun captureImage(onImageSelected: (uri: Uri) -> Unit) {
        this.onImageSelected = onImageSelected
        ImagePicker.with(this)
            .compress(1024)         //Final image size will be less than 1 MB(Optional)
            .maxResultSize(1080, 1080)  //Final image resolution will be less than 1080 x 1080(Optional)
            .createIntent { intent ->
                startForImageResult.launch(intent)
            }

    }


}