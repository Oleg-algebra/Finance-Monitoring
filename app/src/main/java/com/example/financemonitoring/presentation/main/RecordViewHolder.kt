package com.example.financemonitoring.presentation.main

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.financemonitoring.R

class RecordViewHolder(view: View):
    RecyclerView.ViewHolder(view){
    val category: TextView = view.findViewById(R.id.categoryName)
    val change: TextView = view.findViewById(R.id.changeSize)
    val cardView: CardView = view.findViewById(R.id.recordCard)
    val updateDate: TextView = view.findViewById(R.id.updateTV)
    val icon: ImageView = view.findViewById(R.id.iconChange)

}