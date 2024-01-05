package com.example.financemonitoring.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.financemonitoring.data.RepositoryImpl
import com.example.financemonitoring.domain.CATEGORY_TYPE
import com.example.financemonitoring.domain.Card
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.usecases.GetFinanceRecordList

class FilterViewModel(application: Application): AndroidViewModel(application) {    //FixMe: Need fix

    private val repo = RepositoryImpl(application)
    private val getRecordsUC = GetFinanceRecordList(repo)

    val categories = mutableListOf<Card>()

    val categoriesLiveData = MutableLiveData<List<Card>>()

    val records: LiveData<List<FinanceRecord>>
        get() = getRecordsUC.getRecordList()


    fun  initData(recordsList: List<FinanceRecord>){
        val setNames = mutableSetOf<String>()
        val setCategory = mutableSetOf<String>()
        try {
            for(record in recordsList){
                setCategory.add(record.category)
            }
        }catch (e: NullPointerException){
            Log.d(TAG, "initData: records empty")
        }

        var id = 0L
        categories.addAll(setCategory.map { Card(id = id++, text = it, type = CATEGORY_TYPE) })
        update()
    }

    fun toggleCard(card: Card){
        val list = categories

        val oldCard = list.find { it.id == card.id }
        list.remove(oldCard)
        list.add(card.copy(isSelected = !card.isSelected))
        update()
    }
    fun update(){
        categories.sortBy { it.id }
        categoriesLiveData.value = categories.toList()
    }

    companion object{
        val TAG = "CCCCCC"
    }
}