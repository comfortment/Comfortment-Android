package com.comfortment.data.model

import androidx.room.Entity
import com.comfortment.data.base.EntityMapper
import com.comfortment.data.base.ModelEntity
import com.comfortment.domain.model.Auth
import com.google.gson.annotations.SerializedName

@Entity(tableName = "auth", primaryKeys = ["userId"])
data class AuthEntity(
    @SerializedName("userId") val userId: String,
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String
) : ModelEntity()

class AuthEntityMapper : EntityMapper<Auth, AuthEntity> {
    override fun mapToDomain(entity: AuthEntity): Auth = Auth(entity.userId, entity.accessToken, entity.refreshToken)

    override fun mapToEntity(model: Auth): AuthEntity = AuthEntity(model.userId, model.accessToken, model.refreshToken)
}