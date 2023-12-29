package com.example.financemonitoring.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.financemonitoring.R
import com.example.financemonitoring.domain.FinanceRecord

class RecordAdapter: RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {


    var records = listOf<FinanceRecord>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    class RecordViewHolder(view: View):
        RecyclerView.ViewHolder(view){
            val name: TextView = view.findViewById(R.id.recordName)
            val category: TextView = view.findViewById(R.id.categoryName)
            val change: TextView = view.findViewById(R.id.changeSize)
            val cardView: CardView = view.findViewById(R.id.recordCard)
            val updateDate: TextView = view.findViewById(R.id.updateTV)

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.finance_record_layout,parent,false)
        return RecordViewHolder(view)
    }

    override fun getItemCount(): Int {
        return records.size
    }

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val record = records[position]
        with(holder){
            name.text = record.name
            category.text= record.category
            change.text = record.change.toString()
            updateDate.text= record.date.toString()

            cardView.setOnClickListener {
                Log.d(TAG, "onBindViewHolder: $record")
            }
        }

    }

    companion object{
        val TAG = "XXXX"
    }
}