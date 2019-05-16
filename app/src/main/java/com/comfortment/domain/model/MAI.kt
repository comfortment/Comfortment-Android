package com.comfortment.domain.model

data class MAI(val id: String,
               val buildingNumber: Int,
               val roomNumber: Int,
               val name: String,
               val phoneNumber: String,
               val disturbTimeRange: List<Int>,
               val acceptedDecibel: Int,
               val hateNoiseDescription:  String,
               val hateSmellDescriptionL: String,
               val etc: String,
               val role: String) : Model()