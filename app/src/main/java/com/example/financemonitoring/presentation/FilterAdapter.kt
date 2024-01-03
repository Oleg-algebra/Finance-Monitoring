package com.example.financemonitoring.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.financemonitoring.R
import com.example.financemonitoring.domain.Card

class FilterAdapter:
    ListAdapter<Card,FilterAdapter.ItemViewHolder>(DiffUtilCardAsync()) {
    class ItemViewHolder(view: View)
        : RecyclerView.ViewHolder(view) {
            val textView: TextView = view.findViewById(R.id.stringTV)
            val cardView: CardView = view.findViewById(R.id.cardSelect)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemLayout = when (viewType){
            ITEM_SELECTED -> R.layout.selected_card
            ITEM_UNSELECTED -> R.layout.unselected_card
            else -> throw IllegalArgumentException("Unknown type")
        }
//        Log.d(TAG, "onCreateViewHolder: ")
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(itemLayout,parent,false)
        return ItemViewHolder(view)
    }

    var clickListener: ((view: View, item: Card)->Unit  )? = null
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Log.e(TAG, "onBindViewHolder: ")
        val card = getItem(position)
        with(holder){
            textView.text = card.text
            cardView.setOnClickListener {
                clickListener?.invoke(cardView,card)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(getItem(position).isSelected){
            1
        }else{
            0
        }
    }

    companion object{
        val TAG = "RRRRR"
        val ITEM_SELECTED = 1
        val ITEM_UNSELECTED = 0
    }
}