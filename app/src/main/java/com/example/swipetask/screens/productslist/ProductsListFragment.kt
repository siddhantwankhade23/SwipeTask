package com.example.swipetask.screens.productslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import com.example.swipetask.R
import com.example.swipetask.screens.addproducts.AddProductDialog
import com.example.swipetask.viewmodels.ProductsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProductsListFragment : Fragment(), ProductsListViewMvx.Listener {

    private lateinit var viewMvc: ProductsListViewMvx

    private val productsListViewModel: ProductsViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewMvc = ProductsListViewMvx(layoutInflater,null)
        (activity as AppCompatActivity).setSupportActionBar(viewMvc.rootView.findViewById(R.id.app_bar))
        return viewMvc.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        viewMvc.createOptionsMenu(menuHost,this)
    }

    override fun onStart() {
        super.onStart()
        viewMvc.registerListener(this)
        fetchProducts()
    }

    override fun onStop() {
        super.onStop()
        viewMvc.unregisterListener(this)
    }

    private fun fetchProducts() {
        productsListViewModel.productsList.observe(this){ productsList->
            val sortedProductsList = productsList.sortedWith(nullsLast(compareByDescending { it.image }))
            viewMvc.bindProducts(sortedProductsList)
            viewMvc.hideProgressIndication()
        }

        viewMvc.showProgressIndication()
        productsListViewModel.fetchProducts()
    }

    override fun onAddProductClicked() {
        val addProductDialog = AddProductDialog()
        addProductDialog.show(childFragmentManager,null)
    }

}