package com.example.swipetask.data.screens.productslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.swipetask.R

class ProductsListFragment : Fragment(), ProductsListViewMvc.Listener {

    private lateinit var viewMvc: ProductsListViewMvc

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewMvc = ProductsListViewMvc(inflater,container)
        return viewMvc.rootView
    }

    companion object {

        @JvmStatic
        fun newInstance() = ProductsListFragment()
    }

    override fun onStart() {
        super.onStart()
        fetchProducts()
    }

    private fun fetchProducts() {

    }

    override fun onAddProductClicked() {

    }
}