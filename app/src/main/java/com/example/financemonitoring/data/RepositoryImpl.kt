package com.example.financemonitoring.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.financemonitoring.domain.FinanceRecord
import com.example.financemonitoring.domain.Repository

class RepositoryImpl: Repository {

    private val liveData: MutableLiveData<List<FinanceRecord>> = MutableLiveData()
    private val list = mutableListOf<FinanceRecord>()
    private var currentId = 0L

    init {
        for(i in 1..10){
            list.remove(FinanceRecord(id = currentId++))
        }
        update()
    }

    private fun update(){
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
        list.remove(record)
        update()
    }
}