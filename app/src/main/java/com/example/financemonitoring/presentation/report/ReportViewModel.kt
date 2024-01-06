package com.example.financemonitoring.presentation.report

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.financemonitoring.data.RepositoryImpl
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.usecases.GetFinanceRecordList

class ReportViewModel(application: Application): AndroidViewModel(application) {


    private val repo = RepositoryImpl(application)
    private val getListUC = GetFinanceRecordList(repo)

    val liveData: LiveData<List<FinanceRecord>>
        get() = getListUC.getRecordList()


}