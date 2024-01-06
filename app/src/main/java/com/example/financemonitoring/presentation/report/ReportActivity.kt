package com.example.financemonitoring.presentation.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.financemonitoring.R
import com.example.financemonitoring.domain.Filter
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.IntentPreprocessing
import com.example.financemonitoring.domain.filter_comands.FilterChain
import java.time.LocalDate

class ReportActivity : AppCompatActivity() {

    private lateinit var recordsRV: RecyclerView
    private lateinit var dateFrameTV: TextView
    private lateinit var incomeTV: TextView
    private lateinit var outcomeTV: TextView
    private lateinit var investmentTV: TextView
    private lateinit var outcomeIcon: ImageView

    private lateinit var viewModel: ReportViewModel
    private lateinit var reportAdapter: ReportAdapter

    private lateinit var filter: Filter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        initViews()
        parseDateFrame()
        registerLiveData()
    }



    fun parseDateFrame(){
        filter = IntentPreprocessing.parseIntent(intent)
        val fromDate = filter.fromDate?.toString()
            ?.split("-".toRegex())
            ?.reversed()
            ?.joinToString(separator = ".")
        val toDate = filter.toDate?.toString()
            ?.split("-".toRegex())
            ?.reversed()
            ?.joinToString(separator = ".")

        dateFrameTV.text = fromDate?.let { "From $it to" }
            ?.let { "$it ${toDate ?: "now"}" }
            ?: toDate?.let { "From beginning to $it" }
            ?: "For all time"



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
            reportAdapter.submitList(createSummary(FilterChain.execute(filter,it)))
        }

    }

    fun createSummary(records: List<FinanceRecord>): List<FinanceRecord>{
        val newList = mutableListOf<FinanceRecord>()
        records.forEach { r ->
            newList.find { it.category == r.category }
                ?.run {
                    newList.remove(this)
                    newList.add(this.copy(change = this.change + r.change))
                }?: newList.add(r)
        }
        var incomeValue = 0L
        var investmentsValue = 0L
        newList.forEach {
            if(it.change > 0){
                incomeValue += it.change
            }else{
                investmentsValue += it.change
            }
        }

        incomeTV.text = incomeValue.toString()
        investmentTV.text = investmentsValue.toString()
        val outcome = incomeValue + investmentsValue
        outcomeTV.text = outcome.toString()
        outcomeIcon.setImageResource(
            if(outcome<0){
                R.drawable.red_triangle
            }else{
                R.drawable.green_triangle
            }
        )
        newList.replaceAll {
            it.copy(date = LocalDate.now())
        }

        return newList
    }
}