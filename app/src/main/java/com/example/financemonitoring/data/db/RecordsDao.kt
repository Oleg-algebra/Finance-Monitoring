package com.example.financemonitoring.data.db

import androidx.core.widget.ListViewAutoScrollHelper
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.financemonitoring.domain.FinanceRecord

@Dao
interface RecordsDao {
    @Query("SELECT * FROM records")
    fun getListRecords(): LiveData<List<RecordEntity>>
    @Query("SELECT * From records where id =:id ")
    fun getRecord(id: Long): LiveData<RecordEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecord(entity: RecordEntity)
    @Delete
    suspend fun deleteRecord(entity: RecordEntity)
}