package com.example.financemonitoring.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.financemonitoring.data.RepositoryImpl
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.usecases.AddFinanceRecord
import com.example.financemonitoring.domain.usecases.GetFinanceRecordList
import com.example.financemonitoring.domain.usecases.RemoveFinanceRecord
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repo = RepositoryImpl(application)
    private val getFRLUseCase = GetFinanceRecordList(repo)
    private val removeFRUseCase = RemoveFinanceRecord(repo)

    private var _liveData =  MutableLiveData<List<FinanceRecord>>()

    val liveData: LiveData<List<FinanceRecord>>
        get() = getFRLUseCase.getRecordList()
//        get() = _liveData


    fun removeRecord(record: FinanceRecord){
        viewModelScope.launch {
            removeFRUseCase.removeRecord(record)
        }

    }


    fun createData(){
        viewModelScope.launch {
            repo.generateData()
        }

    }
}