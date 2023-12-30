package com.example.financemonitoring.data.mapper

import com.example.financemonitoring.data.db.RecordEntity
import com.example.financemonitoring.domain.FinanceRecord

object Mapper {

    fun mapEntityToRecord(entity: RecordEntity): FinanceRecord{
        return FinanceRecord(
            id = entity.id,
            name = entity.name,
            change = entity.change,
            category = entity.category,
            date = entity.updateDate
        )
    }

    fun mapRecordToEntity(record: FinanceRecord): RecordEntity{
        return RecordEntity(
            id = record.id,
            name = record.name,
            change = record.change,
            category = record.category,
            updateDate = record.date
        )
    }

    fun entitiesToRecords(entities: List<RecordEntity>): List<FinanceRecord>{
        return entities.map { mapEntityToRecord(it) }
    }


}