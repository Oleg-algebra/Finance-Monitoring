package com.example.financemonitoring.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RecordEntity::class], version = 1)
abstract class RecordsDataBase: RoomDatabase() {
    abstract fun getDao(): RecordsDao

    companion object{
        @Volatile
        private var INSTANCE: RecordsDataBase? = null

        fun getInstance(context: Context): RecordsDataBase{
            return INSTANCE ?: synchronized(this){
                INSTANCE?.let { return it }
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecordsDataBase::class.java,
                    "records_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }

        }
    }
}