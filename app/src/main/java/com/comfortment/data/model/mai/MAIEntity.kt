package com.comfortment.data.model.mai

import com.comfortment.data.base.EntityMapper
import com.comfortment.data.base.ModelEntity
import com.comfortment.domain.model.MAI
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class MAIEntity(
    @SerializedName("id") val id: String,
    @SerializedName("buildingNumber") val buildingNumber: Int,
    @SerializedName("roomNumber") val roomNumber: Int,
    @SerializedName("name") val name: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    @SerializedName("disturbTimeRange") val disturbTimeRange: List<Pair<Int, Int>>,
    @SerializedName("acceptedDecibel") val acceptedDecibel: Int,
    @SerializedName("hateNoiseDescription") val hateNoiseDescription: String,
    @SerializedName("hateSmellDescription") val hateSmellDescription: String,
    @SerializedName("etc") val etc: String,
    @SerializedName("role") val role: String
) : ModelEntity()

class MAIEntityMapper @Inject constructor() : EntityMapper<MAI, MAIEntity> {
    override fun mapToDomain(entity: MAIEntity): MAI =
        MAI(
            entity.id,
            entity.buildingNumber,
            entity.roomNumber,
            entity.name,
            entity.phoneNumber,
            entity.disturbTimeRange,
            entity.acceptedDecibel,
            entity.hateNoiseDescription,
            entity.hateSmellDescription,
            entity.etc,
            entity.role
        )

    override fun mapToEntity(model: MAI): MAIEntity =
        MAIEntity(
            model.id,
            model.buildingNumber,
            model.roomNumber,
            model.name,
            model.phoneNumber,
            model.disturbTimeRange,
            model.acceptedDecibel,
            model.hateNoiseDescription,
            model.hateSmellDescription,
            model.etc,
            model.role
        )
}