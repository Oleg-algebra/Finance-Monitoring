package com.example.financemonitoring.domain

import androidx.lifecycle.LiveData

interface Repository {

    fun addRecord(record: FinanceRecord)
    fun editRecord(record: FinanceRecord)
    fun getRecord(id: Long): FinanceRecord
    fun getListRecord(): LiveData<List<FinanceRecord>>
    fun removeRecord(record: FinanceRecord)

}