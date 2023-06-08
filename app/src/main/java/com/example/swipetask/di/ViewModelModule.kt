package com.example.swipetask.di

import com.example.swipetask.viewmodels.ProductsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        ProductsViewModel(get())
    }
}