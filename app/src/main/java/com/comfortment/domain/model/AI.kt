package com.comfortment.domain.model

import java.util.*

data class AI(val uuid: UUID,
              val buildingNumber: Int,
              val roomNumber: Int,
              val name: String,
              val phoneNumber: String,
              val disturbTimeRange: List<Int>,
              val acceptedDecibel: Int,
              val hateNoiseDescription:  String,
              val hateSmellDescriptionL: String,
              val etc: String,
              val role: String)