package com.comfortment.domain.model

data class Auth constructor(
    val userId: String,
    val accessToken: String,
    val refreshToken: String
) : Model() {
    constructor() : this("","","")
}