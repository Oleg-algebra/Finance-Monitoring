package com.example.financemonitoring.presentation

import android.app.Application
import android.text.Editable
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.financemonitoring.data.RepositoryImpl
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.usecases.AddFinanceRecord
import com.example.financemonitoring.domain.usecases.EditFinanceRecord
import com.example.financemonitoring.domain.usecases.GetFinanceRecord
import com.example.financemonitoring.presentation.MainActivity.Companion.TAG
import kotlinx.coroutines.launch
import java.time.LocalDate

class RecordViewModel(application: Application)
    :AndroidViewModel(application){

    private val repository = RepositoryImpl(application)   // FIXME: це на зараз!!! Це не правильно з точки зору чистої архітектури,
    // FIXME: бо presentation модуль стає залежним від data модуля

    private val editFRUC = EditFinanceRecord(repository)
    private val addFRUC = AddFinanceRecord(repository)
    private val getFRUC = GetFinanceRecord(repository)

    private var _recordLiveData = MutableLiveData<FinanceRecord>()
    val recordLiveData: LiveData<FinanceRecord>
        get() = _recordLiveData

    fun getRecord(id: Long){
        viewModelScope.launch {
            _recordLiveData = getFRUC.getRecord(id) as MutableLiveData<FinanceRecord>
        }

    }


    private val _errorChangeLD = MutableLiveData<Boolean>()
    val errorChangeLD: LiveData<Boolean>
        get() = _errorChangeLD

    private val _errorCategoryLD = MutableLiveData<Boolean>()
    val errorCategoryLD: LiveData<Boolean>
        get() = _errorCategoryLD

    private val _errorDateLD = MutableLiveData<Boolean>()
    val errorDateLD: LiveData<Boolean>
        get() = _errorDateLD


    private fun validate(change: Long, category: String, date: String): Boolean {
        var res = true

        if (change == 0L){
            _errorChangeLD.value = false
            res = false
        }
        if(category == "") {
            _errorCategoryLD.value = false
            res = false
        }

        try {
            parseDate(date)
        }catch (e: Exception) {
            _errorDateLD.value = false
            res = false
        }


        return res
    }

    private fun parseInputString(inputName: Editable?) = inputName?.toString() ?: ""

    private fun parseInputCount(inputCount: Editable?) = try {
        inputCount?.toString()?.toLong() ?: 0L
    } catch (er: NumberFormatException){
        0
    }
    private fun parseDate(date: String): LocalDate {
        return date.split("-".toRegex()).let {
            LocalDate.of(it[0].toInt(),it[1].toInt(),it[2].toInt())
        }
    }


    private val _finishActivityLD = MutableLiveData<Unit>()
    val finishActivityLD: LiveData<Unit>
        get() = _finishActivityLD

    fun editRecord(inputChange: Editable?,
                   inputCategory: Editable?, inputDate: Editable?){
        val record = _recordLiveData.value

        val change = parseInputCount(inputChange)
        val category = parseInputString(inputCategory)
        val date = parseInputString(inputDate)
        if (validate(change,category, date)){
            record?.let {
                val financeRecord = it.copy(
                    id = it.id,
                    change = change,
                    category = category,
                    date = parseDate(date))
                viewModelScope.launch {
                    editFRUC.editRecord(financeRecord)
                }

            }
            _finishActivityLD.value = Unit
        }

    }
    fun addRecord(inputCount: Editable?,
                  inputCategory: Editable?, inputDate: Editable?){
        val change = parseInputCount(inputCount)
        val category = parseInputString(inputCategory)
        val date = parseInputString(inputDate)
        if (validate(change,category, date)){

            val financeRecord = FinanceRecord(
                change = change,
                category = category,
                date = parseDate(date))
            viewModelScope.launch {
                addFRUC.addRecord(financeRecord)
            }

            _finishActivityLD.value = Unit
        }
    }

}
