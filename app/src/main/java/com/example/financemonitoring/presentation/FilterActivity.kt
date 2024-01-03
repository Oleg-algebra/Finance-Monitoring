package com.example.financemonitoring.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financemonitoring.R
import com.example.financemonitoring.domain.Card

class FilterActivity : AppCompatActivity() {

    private lateinit var rvNames: RecyclerView
    private lateinit var rvCategoty: RecyclerView
    private lateinit var viewModel: FilterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        rvNames = findViewById(R.id.namesRV)
        rvCategoty = findViewById(R.id.categoryRV)

        viewModel = ViewModelProvider(this)[FilterViewModel::class.java]

        val adapterNames = FilterAdapter()
        val adapterCategory = FilterAdapter()

        viewModel.namesLiveData.observe(this){
            Toast.makeText(this,"names updated",Toast.LENGTH_SHORT).show()
            adapterNames.submitList(it)
        }
        viewModel.categoriesLiveData.observe(this){
            Toast.makeText(this,"categories updated",Toast.LENGTH_SHORT).show()
            adapterCategory.submitList(it)
        }
        adapterNames.clickListener = { view: View, card: Card ->
            Toast.makeText(this@FilterActivity, card.text,Toast.LENGTH_SHORT).show()
            viewModel.toggleCard(card)
        }
        adapterCategory.clickListener = { view: View, card: Card ->
            Toast.makeText(this@FilterActivity, card.text,Toast.LENGTH_SHORT).show()
            viewModel.toggleCard(card)
        }

        rvNames.adapter = adapterNames
        rvCategoty.adapter = adapterCategory

        rvNames.layoutManager = GridLayoutManager(this,3)
        rvCategoty.layoutManager = GridLayoutManager(this,3)
    }

}