package com.example.financemonitoring.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.financemonitoring.data.db.RecordsDataBase
import com.example.financemonitoring.data.mapper.Mapper
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.Repository
import com.example.financemonitoring.presentation.MainActivity.Companion.TAG
import kotlin.random.Random

class RepositoryImpl(application: Application): Repository {

    private var currentId = 1L
    private val dao = RecordsDataBase.getInstance(application).getDao()

    override suspend fun addRecord(record: FinanceRecord) {
        Log.d(TAG, "addRecord: ")
        dao.addRecord(Mapper.mapRecordToEntity(record))
    }

    override suspend fun editRecord(record: FinanceRecord) {
        Log.d(TAG, "editRecord: ")
        addRecord(record)

    }

    override suspend fun getRecord(id: Long): LiveData<FinanceRecord> {

        return MediatorLiveData<FinanceRecord>().apply {
            addSource(dao.getRecord(id)){
                value = Mapper.mapEntityToRecord(it)
            }
        }
    }

    override fun getListRecord(): LiveData<List<FinanceRecord>> {
        return MediatorLiveData<List<FinanceRecord>>().apply {
            addSource(dao.getListRecords()){
                value = Mapper.entitiesToRecords(it)
            }
        }
    }

    override suspend fun removeRecord(record: FinanceRecord) {
        dao.deleteRecord(Mapper.mapRecordToEntity(record))
    }

    suspend fun generateData(){
        dao.clearTable()
       for(i in 1..10){
            dao.addRecord(Mapper.mapRecordToEntity(
                FinanceRecord(
                    name = "Record_$currentId",
                    change = Random.nextLong(20,50))))
           currentId++
        }
    }
}