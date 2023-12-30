package com.example.financemonitoring.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.financemonitoring.R
import com.example.financemonitoring.domain.FinanceRecord

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: RecordAdapter
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewList)
        adapter = RecordAdapter()
        recyclerView.adapter = adapter



        viewModel = ViewModelProvider(this)[MainViewModel::class.java]



        viewModel.liveData.observe(this){
            adapter.submitList(it)
        }
        adapter.swipeListener = {
            viewModel.removeRecord(it)
        }
        val itemTouchHelper = ItemTouchHelper(adapter.simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)


    }




    companion object{
        val TAG = "XXXXX"
    }
}