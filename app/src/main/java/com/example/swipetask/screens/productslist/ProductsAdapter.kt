package com.example.swipetask.screens.productslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.swipetask.R
import com.example.swipetask.data.model.Product
import com.example.swipetask.databinding.LayoutProductListItemBinding

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.QuestionViewHolder>() {

    private var productsList: List<Product> = ArrayList(0)

    inner class QuestionViewHolder(private val productsItemViewBinding: LayoutProductListItemBinding) :
        RecyclerView.ViewHolder(productsItemViewBinding.root) {
        fun bind(product: Product) {
            productsItemViewBinding.product = product
            product.image?.let {
                if (it.isNotBlank()) {
                    Glide.with(productsItemViewBinding.root.context).load(product.image)
                        .apply(RequestOptions().centerCrop())
                        .into(productsItemViewBinding.ivProduct)
                } else {
                    productsItemViewBinding.ivProduct.setImageDrawable(
                        AppCompatResources.getDrawable(
                            productsItemViewBinding.root.context,
                            R.drawable.logo
                        )
                    )
                }
            }

        }
    }

    fun bindData(products: List<Product>) {
        productsList = ArrayList(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val productsItemViewBinding =
            LayoutProductListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(productsItemViewBinding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(productsList[position])

    }

    override fun getItemCount(): Int {
        return productsList.size
    }

}