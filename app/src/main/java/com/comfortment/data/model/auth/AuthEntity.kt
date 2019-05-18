package com.comfortment.data.model.auth

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.comfortment.data.base.EntityMapper
import com.comfortment.data.base.ModelEntity
import com.comfortment.domain.model.Auth
import com.google.gson.annotations.SerializedName

@Entity(tableName = "auth")
data class AuthEntity(
    @SerializedName("userId") @PrimaryKey @ColumnInfo(name = "userId") val userId: String,
    @SerializedName("accessToken") @ColumnInfo(name = "accessToken") val accessToken: String,
    @SerializedName("refreshToken") @ColumnInfo(name = "refreshToken") val refreshToken: String
) : ModelEntity()

class AuthEntityMapper : EntityMapper<Auth, AuthEntity> {
    override fun mapToDomain(entity: AuthEntity): Auth = Auth(entity.userId, entity.accessToken, entity.refreshToken)

    override fun mapToEntity(model: Auth): AuthEntity =
        AuthEntity(model.userId, model.accessToken, model.refreshToken)
}