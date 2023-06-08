package com.example.swipetask.screens.productslist

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swipetask.R
import com.example.swipetask.data.model.Product
import com.example.swipetask.screens.common.viewsmvc.BaseViewMvc
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.progressindicator.CircularProgressIndicator

class ProductsListViewMvx(
    inflater: LayoutInflater,
    parent: ViewGroup?
) : BaseViewMvc<ProductsListViewMvx.Listener>(
    inflater,
    parent,
    R.layout.fragment_products_list
) {

    interface Listener {
        fun onAddProductClicked()
    }

    private val fabAddProduct: FloatingActionButton
    private val progressIndicator : CircularProgressIndicator
    private val recyclerView: RecyclerView
    private val productsAdapter: ProductsAdapter

    init {

        fabAddProduct = findViewById(R.id.floating_action_button)
        fabAddProduct.setOnClickListener {
            for (listener in listeners) {
                listener.onAddProductClicked()
            }
        }

        progressIndicator = findViewById(R.id.progressIndicator)

        // init recycler view
        recyclerView = findViewById(R.id.recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        productsAdapter = ProductsAdapter()

        recyclerView.adapter = productsAdapter
    }

    fun createOptionsMenu(menuHost: MenuHost){

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.toolbar_menu, menu)
            }

            /**
             * Responding to the user selecting an item in the menu, in this case
             * adding a new crime. Several menu items can be added here.
             */
            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                // Handle the menu selection
                return when (menuItem.itemId) {
                    R.id.search -> {

                        true
                    }
                    else -> false
                }
            }
        })
    }

    fun bindProducts(products: List<Product>) {
        productsAdapter.bindData(products)
    }

    fun showProgressIndication() {
        progressIndicator.visibility = View.VISIBLE
    }

    fun hideProgressIndication() {
        progressIndicator.visibility = View.GONE
    }
}