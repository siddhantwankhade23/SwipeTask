package com.example.swipetask.data.screens

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.swipetask.R
import com.example.swipetask.data.screens.productslist.ProductsListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .add(R.id.frame_content, ProductsListFragment())
            .commit()
    }
}