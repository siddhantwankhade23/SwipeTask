package com.example.swipetask.screens.productslist

import android.util.Log
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.swipetask.R
import com.example.swipetask.data.model.Product
import com.example.swipetask.screens.common.viewsmvc.BaseViewMvc
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import java.util.Locale


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
    private lateinit var productList : List<Product>

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

    fun createOptionsMenu(menuHost: MenuHost, lifecycleOwner : LifecycleOwner){

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                // Add menu items here
                menuInflater.inflate(R.menu.toolbar_menu, menu)

                val searchItem = menu.findItem(R.id.search)

                val searchView: SearchView = searchItem.actionView as SearchView

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        // inside on query text change method we are
                        // calling a method to filter our recycler view.
                        filter(newText)
                        return false
                    }
                })
            }

            private fun filter(text: String) {
                val filteredlist = ArrayList<Product>()

                for (item in productList) {
                    item.productName?.let {
                        Log.d("Siddhant"," "+it+" : "+text.lowercase(Locale.getDefault()))
                        if (it.lowercase().contains(text.lowercase(Locale.getDefault()))) {
                            filteredlist.add(item)
                        }
                    }

                }
                if (filteredlist.isEmpty()) {
                    // if no item is added in filtered list we are
                    // displaying a toast message as no data found.
                    Toast.makeText(rootView.context, "No Data Found..", Toast.LENGTH_SHORT).show()
                } else {
                    // at last we are passing that filtered
                    // list to our adapter class.
                    productsAdapter.bindData(filteredlist)
                }
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
        },lifecycleOwner, Lifecycle.State.RESUMED)
    }

    fun bindProducts(products: List<Product>) {
        productList = products
        productsAdapter.bindData(products)
    }

    fun showProgressIndication() {
        progressIndicator.visibility = View.VISIBLE
    }

    fun hideProgressIndication() {
        progressIndicator.visibility = View.GONE
    }
}