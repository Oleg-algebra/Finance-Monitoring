package com.example.financemonitoring.presentation.report

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.financemonitoring.R
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.presentation.main.DiffUtilRecordsAsync
import com.example.financemonitoring.presentation.main.RecordViewHolder

class ReportAdapter:
    ListAdapter<FinanceRecord,RecordViewHolder>(DiffUtilRecordsAsync()){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.finance_record_layout,parent,false)
        return RecordViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val record = getItem(position)
        with(holder){
            category.text= record.category
            change.text = record.change.toString()
            updateDate.text= record.date.toString()
            icon.setImageResource(
                if(record.change < 0){
                    R.drawable.red_triangle
                }else{
                    R.drawable.green_triangle
                }
            )
//            cardView.setOnClickListener {
//                clickListener?.invoke(cardView,record)
//            }
        }
    }
}