package com.example.financemonitoring.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.financemonitoring.data.RepositoryImpl
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.usecases.AddFinanceRecord
import com.example.financemonitoring.domain.usecases.GetFinanceRecordList
import com.example.financemonitoring.domain.usecases.RemoveFinanceRecord

class MainViewModel: ViewModel() {

    private val repo = RepositoryImpl()
    private val addRecordUseCase = AddFinanceRecord(repo)
    private val getFRLUseCase = GetFinanceRecordList(repo)
    private val removeFRUseCase = RemoveFinanceRecord(repo)

    val liveData: LiveData<List<FinanceRecord>>
        get() = getFRLUseCase.getRecordList()


    fun getRecordsList(){
        getFRLUseCase.getRecordList()
    }

    fun removeRecord(record: FinanceRecord){
        removeFRUseCase.removeRecord(record)
    }

    fun addRecord(record: FinanceRecord){
        addRecordUseCase.addRecord(record)
    }
}