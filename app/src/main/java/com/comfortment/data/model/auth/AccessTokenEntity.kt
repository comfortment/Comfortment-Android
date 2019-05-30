package com.comfortment.data.model.auth

import com.google.gson.annotations.SerializedName

data class AccessTokenEntity(@SerializedName("accessToken") val accessToken: String)