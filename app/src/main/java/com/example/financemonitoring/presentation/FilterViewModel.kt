package com.example.financemonitoring.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.financemonitoring.domain.CATEGORY_TYPE
import com.example.financemonitoring.domain.Card
import com.example.financemonitoring.domain.NAME_TYPE

class FilterViewModel: ViewModel() {    //FixMe: Need fix

    val names = mutableListOf<Card>()
    val categories = mutableListOf<Card>()

    val namesLiveData = MutableLiveData<List<Card>>()
    val categoriesLiveData = MutableLiveData<List<Card>>()

    init {

        for(i in 1..30){
            names.add(Card(id = i.toLong(),text = "Name_$i", type = NAME_TYPE))
        }
        namesLiveData.value = names.toList()


        for(i in 1..30){
            categories.add(Card(id = i.toLong(),text = "category_$i", type = CATEGORY_TYPE))
        }
        categoriesLiveData.value = categories.toList()
    }

    fun toggleCard(card: Card){
        val list = if(card.type == NAME_TYPE){
            names
        }else{
            categories
        }

        val oldCard = list.find { it.id == card.id }
        list.remove(oldCard)
        list.add(card.copy(isSelected = !card.isSelected))
        update()
    }
    fun update(){
        names.sortBy { it.id }
        namesLiveData.value = names.toList()
        categories.sortBy { it.id }
        categoriesLiveData.value = categories.toList()
    }
}