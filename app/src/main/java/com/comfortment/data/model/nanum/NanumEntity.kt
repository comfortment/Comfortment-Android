package com.comfortment.data.model.nanum

import com.comfortment.data.base.EntityMapper
import com.comfortment.data.base.ModelEntity
import com.comfortment.domain.model.Nanum
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class NanumEntity(
    @SerializedName("nanumId") val nanumId: String,
    // from MAI
    @SerializedName("roomNumber") val roomNumber: Int,
    @SerializedName("ownerName") val ownerName: String,
    @SerializedName("phoneNumber") val phoneNumber: String,
    // from Nanum
    @SerializedName("type") val type: String,
    @SerializedName("bankAccount") val bankAccount: String?,
    @SerializedName("bank") val bank: String?,
    @SerializedName("imagePath") val imagePath: String?,
    @SerializedName("price") val price: Int,
    @SerializedName("expiry") val expiry: Int, // 1h == 1
    @SerializedName("description") val description: String?,
    @SerializedName("referTo") val referTo: String?,
    @SerializedName("payAt") val payAt: String?,
    @SerializedName("title") val title: String,
    @SerializedName("currentState") val currentState: String
) : ModelEntity()

class NanumMapper @Inject constructor() : EntityMapper<Nanum, NanumEntity> {

    override fun mapToEntity(model: Nanum): NanumEntity =
        NanumEntity(
            model.nanumId,
            model.roomNumber,
            model.ownerName,
            model.phoneNumber,
            model.type,
            model.bankAccount,
            model.bank,
            model.imagePath,
            model.price,
            model.expiry,
            model.description,
            model.referTo,
            model.payAt,
            model.title,
            model.currentState
        )

    override fun mapToDomain(entity: NanumEntity): Nanum =
        Nanum(
            entity.nanumId,
            entity.roomNumber,
            entity.ownerName,
            entity.phoneNumber,
            entity.type,
            entity.bankAccount,
            entity.bank,
            entity.imagePath,
            entity.price,
            entity.expiry,
            entity.description,
            entity.referTo,
            entity.payAt,
            entity.title,
            entity.currentState
        )
}