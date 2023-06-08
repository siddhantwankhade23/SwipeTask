package com.example.swipetask.data.screens.productslist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.swipetask.R
import com.example.swipetask.data.model.Product
import com.example.swipetask.data.screens.common.viewsmvc.BaseViewMvc

class ProductsListViewMvc(
    inflater: LayoutInflater,
    parent: ViewGroup?
) : BaseViewMvc<ProductsListViewMvc.Listener>(
    inflater,
    parent,
    R.layout.fragment_products_list
) {

    interface Listener {
        fun onAddProductClicked()
    }

    private val swipeRefresh: SwipeRefreshLayout
    private val recyclerView: RecyclerView
    private val productsAdapter: ProductsAdapter

    init {

        swipeRefresh = findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {

        }

        // init recycler view
        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        productsAdapter = ProductsAdapter()

        recyclerView.adapter = productsAdapter
    }

    fun bindProducts(products: List<Product>) {
        productsAdapter.bindData(products)
    }

    fun showProgressIndication() {
        swipeRefresh.isRefreshing = true
    }

    fun hideProgressIndication() {
        swipeRefresh.isRefreshing = false
    }
}