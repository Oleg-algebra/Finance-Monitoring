package com.example.financemonitoring.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.financemonitoring.R
import com.example.financemonitoring.domain.FinanceRecord

class RecordAdapter:
    ListAdapter<FinanceRecord, RecordAdapter.RecordViewHolder>(DiffUtilRecordsAsync()){

    class RecordViewHolder(view: View):
        RecyclerView.ViewHolder(view){
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

    var clickListener: ((view: View, record: FinanceRecord)->Unit)? = null
    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) {
        val record = getItem(position)
        with(holder){
            category.text= record.category
            change.text = record.change.toString()
            updateDate.text= record.date.toString()

            cardView.setOnClickListener {
                clickListener?.invoke(cardView,record)
            }
        }

    }
    var swipeListener: ((record: FinanceRecord) -> Unit)? = null
    var simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
        ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
//                    or ItemTouchHelper.DOWN or ItemTouchHelper.UP
        ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
            val position = viewHolder.adapterPosition
            val shopItem = getItem(position)
            swipeListener?.invoke(shopItem)
        }
    }

}