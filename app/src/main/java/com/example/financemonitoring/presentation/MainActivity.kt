package com.example.financemonitoring.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.example.financemonitoring.R
import com.example.financemonitoring.domain.FinanceRecord

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var listButton: Button
    private lateinit var removeButton: Button
    private lateinit var record: FinanceRecord
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        viewModel.liveData.observe(this){
            Log.d(TAG, "onCreate: $it ")
            if(it.isNotEmpty()){
                record = it[0]
            }
        }

        listButton.setOnClickListener {
            viewModel.getRecordsList()
        }
        removeButton.setOnClickListener {
            viewModel.removeRecord(record)
        }
    }

    fun initViews(){
        listButton = findViewById(R.id.showList)
        removeButton = findViewById(R.id.removeButton)
    }


    companion object{
        val TAG = "XXXXX"
    }
}