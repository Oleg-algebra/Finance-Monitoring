package com.example.financemonitoring.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.Repository
import com.example.financemonitoring.presentation.MainActivity.Companion.TAG

class RepositoryImpl: Repository {

    private val liveData: MutableLiveData<List<FinanceRecord>> = MutableLiveData()
    private val list = mutableListOf<FinanceRecord>()
    private var currentId = 0L

    init {
        Log.d(TAG, "Repo Impl init ")
        for(i in 1..10){
            list.add(FinanceRecord(id = currentId++))
        }
        update()
    }

    private fun update(){
        Log.d(TAG, "update: ")
        liveData.value = list
    }
    override fun addRecord(record: FinanceRecord) {
        list.add(record)
        update()
    }

    override fun editRecord(record: FinanceRecord) {
        val oldRecord = getRecord(record.id)
        list.remove(oldRecord)
        addRecord(record)
    }

    override fun getRecord(id: Long): FinanceRecord {
        return list.find { it.id == id } ?:
            throw RuntimeException("Record with id: $id not found")
    }

    override fun getListRecord(): LiveData<List<FinanceRecord>> {
        return liveData.apply { update() }
    }

    override fun removeRecord(record: FinanceRecord) {
        Log.d(TAG, "removeRecord: ${record.id} removed")
        list.remove(record)
        update()
    }
}