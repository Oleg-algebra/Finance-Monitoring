package com.example.financemonitoring.presentation.filter

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financemonitoring.R
import com.example.financemonitoring.domain.Card
import com.example.financemonitoring.domain.EXTRA_CATEGORIES
import com.example.financemonitoring.domain.EXTRA_FROM_DATE
import com.example.financemonitoring.domain.EXTRA_TO_DATE
import com.example.financemonitoring.domain.Filter
import com.example.financemonitoring.presentation.main.MainActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class FilterActivity : AppCompatActivity() {

    private lateinit var rvCategoty: RecyclerView
    private lateinit var viewModel: FilterViewModel

    private lateinit var fromDateTIL: TextInputLayout
    private lateinit var toDateTIL: TextInputLayout
    private lateinit var editFromDate: TextInputEditText
    private lateinit var editToDate: TextInputEditText

    private lateinit var makeFilterButton: Button
    private lateinit var makeReportButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        initViews()
        registerLiveData()

    }

    fun initViews(){
        rvCategoty = findViewById(R.id.categoryRV)


        fromDateTIL = findViewById(R.id.fromDateTIL)
        toDateTIL = findViewById(R.id.toDateTIL)
        editFromDate = findViewById(R.id.editFromDate)
        editToDate = findViewById(R.id.editToDate)

        makeFilterButton = findViewById(R.id.createFilterButton)
        makeReportButton = findViewById(R.id.makeReportButton)

        editFromDate.addTextChangedListener {
            fromDateTIL.error = null
        }
        editToDate.addTextChangedListener {
            toDateTIL.error = null
        }

    }

    fun registerLiveData(){

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

        rvCategoty.layoutManager = GridLayoutManager(this,2)

        viewModel.errorFromDateLD.observe(this){
            fromDateTIL.error = if (it){
                null
            }else{
                "Date error"
            }
        }

        viewModel.errorToDateLD.observe(this){
            toDateTIL.error = if(it){
                null
            }else{
                "Date error"
            }
        }
        viewModel.filterLD.observe(this){
            sentIntent(it)
        }

        makeFilterButton.setOnClickListener {
            viewModel.makeFilter(editFromDate.text,
                editToDate.text)
        }
        makeReportButton.setOnClickListener {
            Toast.makeText(this,"report button",Toast.LENGTH_SHORT).show()
        }
    }
    fun sentIntent(filter: Filter){

        val intent = Intent(this,MainActivity::class.java)
//        Log.d(TAG, "sentIntent categories: ${filter.categories?.joinToString()} ")
        filter.categories?.let{intent.putExtra(EXTRA_CATEGORIES,it.joinToString(separator = ","))}
        filter.fromDate?.let { intent.putExtra(EXTRA_FROM_DATE,it.toString()) }
        filter.toDate?.let { intent.putExtra(EXTRA_TO_DATE,it.toString()) }

        startActivity(intent)
    }
    companion object{
        val TAG = "XXXXX"
    }

}