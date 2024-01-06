package com.example.financemonitoring.presentation.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.financemonitoring.R

class ReportActivity : AppCompatActivity() {

    private lateinit var recordsRV: RecyclerView
    private lateinit var dateFrameTV: TextView
    private lateinit var incomeTV: TextView
    private lateinit var outcomeTV: TextView
    private lateinit var investmentTV: TextView
    private lateinit var outcomeIcon: ImageView

    private lateinit var viewModel: ReportViewModel
    private lateinit var reportAdapter: ReportAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        initViews()
        registerLiveData()

    }

    fun initViews(){
        dateFrameTV = findViewById(R.id.timeFrame)
        incomeTV = findViewById(R.id.incomeValue)
        outcomeTV =  findViewById(R.id.outcomeValue)
        investmentTV = findViewById(R.id.investmentValue)
        outcomeIcon = findViewById(R.id.iconOutcome)
        recordsRV = findViewById(R.id.recordsRV)

        reportAdapter = ReportAdapter()
        recordsRV.adapter = reportAdapter

    }
    fun registerLiveData(){

        viewModel = ViewModelProvider(this)[ReportViewModel::class.java]
        viewModel.liveData.observe(this){
            reportAdapter.submitList(it)
        }

    }
}