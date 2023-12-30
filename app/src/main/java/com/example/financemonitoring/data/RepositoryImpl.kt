package com.example.financemonitoring.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.financemonitoring.data.db.RecordsDataBase
import com.example.financemonitoring.data.mapper.Mapper
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.Repository
import com.example.financemonitoring.presentation.MainActivity.Companion.TAG
import kotlin.coroutines.suspendCoroutine

class RepositoryImpl(val application: Application): Repository {

    private val liveData: MutableLiveData<List<FinanceRecord>> = MutableLiveData()
    private val list = mutableListOf<FinanceRecord>()
    private var currentId = 0L
    private val dao = RecordsDataBase.getInstance(application).getDao()

//    init {
//        Log.d(TAG, "Repo Impl init ")
//

////        update()
//    }
//
//    private fun update(){
//        liveData.value = list.toList()
//    }
    override suspend fun addRecord(record: FinanceRecord) {
        dao.addRecord(Mapper.mapRecordToEntity(record))
//        update()
    }

    override suspend fun editRecord(record: FinanceRecord) {
        addRecord(record)
//        val oldRecord = getRecord(record.id)
//        list.remove(oldRecord)
//        addRecord(record)
    }

    override fun getRecord(id: Long): LiveData<FinanceRecord> {
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
//        list.remove(record)
//        update()
    }

    suspend fun generateData(){
       for(i in 1..10){
            dao.addRecord(Mapper.mapRecordToEntity(
                FinanceRecord(id = currentId++)))
        }
    }
}