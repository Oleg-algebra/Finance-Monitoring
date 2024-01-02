package com.example.financemonitoring.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.financemonitoring.domain.Card

class FilterViewModel: ViewModel() {

    val names = mutableListOf<Card>()
    val categories = mutableListOf<Card>()

    val namesLiveData = MutableLiveData<List<Card>>()
    val categoriesLiveData = MutableLiveData<List<Card>>()

    init {

        for(i in 1..30){
            names.add(Card(id = i.toLong(),text = "Name_$i"))
        }
        namesLiveData.value = names


        for(i in 1..30){
            categories.add(Card(id = i.toLong(),text = "category_$i"))
        }
        categoriesLiveData.value = categories
    }

    fun editCard(card: Card){
        var oldCard = names.find { it.text == card.text }
        if (oldCard != null){
            names.remove(oldCard)
            names.add(card)
            update()
        }else {
            oldCard = categories.find { it.text == card.text }
            if (oldCard != null) {
                categories.remove(oldCard)
                categories.add(card)
            }
            update()
        }
    }
    fun update(){
        names.sortBy { it.id }
        namesLiveData.value = names
        categories.sortBy { it.id }
        categoriesLiveData.value = categories
    }
}