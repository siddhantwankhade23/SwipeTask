package com.example.swipetask.data.screens.productslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swipetask.data.model.Product
import com.example.swipetask.databinding.LayoutProductListItemBinding

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.QuestionViewHolder>() {

    private var productsList: List<Product> = ArrayList(0)

    inner class QuestionViewHolder(private val productsItemViewBinding: LayoutProductListItemBinding) : RecyclerView.ViewHolder(productsItemViewBinding.root){
        fun bind(product: Product) {
            productsItemViewBinding.product = product

        }
    }

    fun bindData(products: List<Product>) {
        productsList = ArrayList(products)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val productsItemViewBinding = LayoutProductListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return QuestionViewHolder(productsItemViewBinding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(productsList[position])

    }

    override fun getItemCount(): Int {
        return productsList.size
    }

}