package com.comfortment.domain.model

/*
NanumType = "bundle" | "joint" | "rummage_sale" | "worker";

PayAt = "Advanced" | "Deferred";

Path = string;

NanumId = string;
 */
data class Nanum(
    val nanumId: String,
    // from MAI
    val roomNumber: Int,
    val ownerName: String,
    val phoneNumber: String,
    // from Nanum
    val type: String,
    val bankAccount: String?,
    val bank: String?,
    val imagePath: String?,
    val price: Int,
    val expiry: Int, // 1h == 1
    val description: String?,
    val referTo: String?,
    val payAt: String?,
    val title: String
) : Model()