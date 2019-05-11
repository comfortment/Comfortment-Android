package com.comfortment.domain.model

import com.google.gson.annotations.SerializedName

data class Auth(
    @SerializedName("userId")
    val userId: String,
    @SerializedName("accessToken")
    val accessToken: String,
    @SerializedName("refreshToken")
    val refreshToken: String)