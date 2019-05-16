package com.comfortment.domain.model

data class Auth(
    val userId: String,
    val accessToken: String,
    val refreshToken: String) : Model()