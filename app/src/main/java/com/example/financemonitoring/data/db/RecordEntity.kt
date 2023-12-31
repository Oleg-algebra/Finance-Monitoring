package com.example.financemonitoring.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "records")
data class RecordEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "record_id")
    val id: Long = 0L,
    @ColumnInfo(name = "record_name")
    val name: String,
    @ColumnInfo(name = "change_size")
    val change: Long,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "update_date")
    val updateDate: String

)
