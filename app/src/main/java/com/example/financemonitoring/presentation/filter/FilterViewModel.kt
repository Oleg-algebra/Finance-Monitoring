package com.example.financemonitoring.presentation.filter

import android.app.Application
import android.text.Editable
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.financemonitoring.data.RepositoryImpl
import com.example.financemonitoring.domain.CATEGORY_TYPE
import com.example.financemonitoring.domain.Card
import com.example.financemonitoring.domain.Date
import com.example.financemonitoring.domain.Filter
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.usecases.GetFinanceRecordList
import java.time.LocalDate

class FilterViewModel(application: Application): AndroidViewModel(application) {    //FixMe: Need fix

    private val repo = RepositoryImpl(application)
    private val getRecordsUC = GetFinanceRecordList(repo)

    val categories = mutableListOf<Card>()

    val categoriesLiveData = MutableLiveData<List<Card>>()

    val records: LiveData<List<FinanceRecord>>
        get() = getRecordsUC.getRecordList()

    private val _errorFromDateLD = MutableLiveData<Boolean>()

    val errorFromDateLD: LiveData<Boolean>
        get() = _errorFromDateLD

    private val _errorToDateLD = MutableLiveData<Boolean>()

    val errorToDateLD: LiveData<Boolean>
        get() = _errorToDateLD


    private val _filterLD = MutableLiveData<Filter>()

    val filterLD: LiveData<Filter>
        get() = _filterLD

    fun  initData(recordsList: List<FinanceRecord>){
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

    fun parseDate(inputDate: Editable?) = inputDate?.toString() ?: ""


    fun validateDate(dateString: String): Boolean{
        if (dateString.isEmpty()){
            return true
        }
        return try {
            Date.stringToDate(dateString)
            true
        }catch (e: Exception){
            false
        }
    }
    fun validate(fromDateString: String,toDateString:String): Boolean{
        var res = true
        if(!validateDate(fromDateString)){
            _errorFromDateLD.value = false
            res = false
        }
        if(!validateDate(toDateString)){
            _errorToDateLD.value = false
            res = false
        }


        return res
    }

    fun makeFilter(inputFromDate: Editable?,
                   inputToDate: Editable?, activity: String) {

        val fromDateString = parseDate(inputFromDate)
        val toDateString = parseDate(inputToDate)
        if(validate(fromDateString, toDateString)){
            val fromDate = if(fromDateString.isEmpty()){
                null
            }else{
                Date.stringToDate(fromDateString)
            }
            val toDate = if(toDateString.isEmpty()){
                null
            }else{
                Date.stringToDate(toDateString)
            }
            val selectedCategories = categories.filter { it.isSelected }
                .map { it.text }
            _filterLD.value = Filter(
                categories = selectedCategories.ifEmpty {null},
                fromDate = fromDate,
                toDate = toDate,
                activity = activity

            )

        }



    }



    companion object{
        val TAG = "CCCCCC"
    }
}