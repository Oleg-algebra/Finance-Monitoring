package com.example.financemonitoring.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface RecordsDao {
    @Query("SELECT * FROM records")
    fun getListRecords(): LiveData<List<RecordEntity>>
    @Query("SELECT * From records where record_id ==:id LIMIT 1")
    fun getRecord(id: Long): LiveData<RecordEntity>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecord(entity: RecordEntity)
    @Delete
    suspend fun deleteRecord(entity: RecordEntity)

    @Query("DELETE FROM records")
    suspend fun clearTable()
}