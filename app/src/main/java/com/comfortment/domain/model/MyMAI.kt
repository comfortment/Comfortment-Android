package com.comfortment.domain.model

data class MyMAI(
    val id: String,
    val buildingNumber: Int,
    val roomNumber: Int
) : Model() {
    constructor() : this("", 0, 0)
}