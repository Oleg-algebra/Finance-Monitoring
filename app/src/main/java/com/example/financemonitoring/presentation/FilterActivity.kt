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

        rvNames = findViewById(R.id.categoryRV)
        rvCategoty = findViewById(R.id.namesRV)

        viewModel = ViewModelProvider(this)[FilterViewModel::class.java]

        val adapterNames = FilterAdapter()
        val adapterCategory = FilterAdapter()

        viewModel.namesLiveData.observe(this){
            adapterNames.submitList(it)
        }
        viewModel.categoriesLiveData.observe(this){
            adapterCategory.submitList(it)
        }
        adapterNames.clickListener = { view: View, card: Card ->
            Toast.makeText(this@FilterActivity, card.text,Toast.LENGTH_SHORT).show()
            card.isSelected = !card.isSelected
            viewModel.editCard(card)
        }
        adapterCategory.clickListener = { view: View, card: Card ->
            Toast.makeText(this@FilterActivity, card.text,Toast.LENGTH_SHORT).show()
            card.isSelected = !card.isSelected
            viewModel.editCard(card)
        }

        rvNames.adapter = adapterNames
        rvCategoty.adapter = adapterCategory

        rvNames.layoutManager = GridLayoutManager(this,3)
        rvCategoty.layoutManager = GridLayoutManager(this,3)



    }
}