package com.example.financemonitoring.presentation

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.financemonitoring.domain.FinanceRecord

class DiffUtilRecordsAsync :
    DiffUtil.ItemCallback<FinanceRecord>(){
    override fun areItemsTheSame(oldItem: FinanceRecord, newItem: FinanceRecord): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: FinanceRecord, newItem: FinanceRecord): Boolean {
        return oldItem==newItem
    }
    companion object{
        val TAG = "XXXX"
    }
}