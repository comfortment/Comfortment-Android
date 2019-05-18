package com.comfortment.data.model.mai

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.comfortment.data.base.EntityMapper
import com.comfortment.data.base.ModelEntity
import com.comfortment.domain.model.MyMAI

@Entity(tableName = "MyMAI")
data class MyMAIEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: String,
    @ColumnInfo(name = "buildingNumber") val buildingNumber: Int,
    @ColumnInfo(name = "roomNumber") val roomNumber: Int
) : ModelEntity()

class MyMAIEntityMapper : EntityMapper<MyMAI, MyMAIEntity> {
    override fun mapToDomain(entity: MyMAIEntity): MyMAI =
        MyMAI(entity.id, entity.buildingNumber, entity.roomNumber)

    override fun mapToEntity(model: MyMAI): MyMAIEntity =
        MyMAIEntity(model.id, model.buildingNumber, model.roomNumber)
}