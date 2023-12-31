package com.example.financemonitoring.presentation

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.financemonitoring.R
import com.example.financemonitoring.domain.EXTRA_ITEM_ID
import com.example.financemonitoring.domain.EXTRA_MODE
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.MODE_ADD
import com.example.financemonitoring.domain.MODE_EDIT
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel

    private lateinit var adapter: RecordAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var addButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addButton = findViewById(R.id.addButton)

        recyclerView = findViewById(R.id.recyclerViewList)
        adapter = RecordAdapter()
        recyclerView.adapter = adapter
        adapter.clickListener = { view: View, record: FinanceRecord ->
            Log.d(TAG, "adapter clickListener: ${record.id} ")
            val intent = Intent(this,RecordActivity::class.java)
                .putExtra(EXTRA_MODE, MODE_EDIT)
                .putExtra(EXTRA_ITEM_ID,record.id)
            startActivity(intent)
        }

        addButton.setOnClickListener {
            Log.d(TAG, "launching add activity: ")
            val intent = Intent(this,RecordActivity::class.java)
                .putExtra(EXTRA_MODE, MODE_ADD)
            startActivity(intent)
        }

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
//        viewModel.createData()

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