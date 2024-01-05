package com.example.financemonitoring.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financemonitoring.R
import com.example.financemonitoring.domain.Card

class FilterActivity : AppCompatActivity() {

    private lateinit var rvCategoty: RecyclerView
    private lateinit var viewModel: FilterViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        initViews()


        viewModel = ViewModelProvider(this)[FilterViewModel::class.java]
        viewModel.records.observe(this){
            viewModel.initData(it)
        }


        val adapterCategory = FilterAdapter()


        viewModel.categoriesLiveData.observe(this){
//            Toast.makeText(this,"categories updated",Toast.LENGTH_SHORT).show()
            adapterCategory.submitList(it)
        }

        adapterCategory.clickListener = { view: View, card: Card ->
//            Toast.makeText(this@FilterActivity, card.text,Toast.LENGTH_SHORT).show()
            viewModel.toggleCard(card)
        }

        rvCategoty.adapter = adapterCategory

        rvCategoty.layoutManager = GridLayoutManager(this,3)
    }

    fun initViews(){
        rvCategoty = findViewById(R.id.categoryRV)


    }

}