package com.example.financemonitoring.data.mapper

import com.example.financemonitoring.data.db.RecordEntity
import com.example.financemonitoring.domain.FinanceRecord
import java.time.LocalDate

object Mapper {

    fun mapEntityToRecord(entity: RecordEntity): FinanceRecord{
        return FinanceRecord(
            id = entity.id,
            name = entity.name,
            change = entity.change,
            category = entity.category,
            date = stringToLocalDate(entity.updateDate)
        )
    }

    fun mapRecordToEntity(record: FinanceRecord): RecordEntity{
        return RecordEntity(
            id = record.id,
            name = record.name,
            change = record.change,
            category = record.category,
            updateDate = record.date.toString()
        )
    }

    fun entitiesToRecords(entities: List<RecordEntity>): List<FinanceRecord>{
        return entities.map { mapEntityToRecord(it) }
    }

    fun stringToLocalDate(stringDate: String):LocalDate{
        return stringDate.split(Regex("-")).let {
            LocalDate.of(it[0].toInt(),it[1].toInt(),it[2].toInt())
        }
    }


}