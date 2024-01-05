package com.example.financemonitoring.presentation

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.financemonitoring.domain.Card
import kotlin.math.log

class DiffUtilCardAsync:
DiffUtil.ItemCallback<Card>(){
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
//        Log.d(TAG, "areItemsTheSame: ")
        return oldItem.text == newItem.text
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
//        Log.d(TAG, "areContentsTheSame: ")
        return oldItem == newItem
    }
    companion object{
        val TAG = "RRRRR"
    }

}
