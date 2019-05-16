package com.comfortment.data.remote.api

import com.comfortment.domain.model.MAI
import io.reactivex.Single
import retrofit2.http.*

interface MaiAPI {

    @GET("apartment")
    fun apartment(
        @Header("Authorization ") accessToken: String,
        @Query("id") id: String
    ): Single<MAI>

    @GET("building")
    fun building(
        @Header("Authorization ") accessToken: String,
        @Query("buildingNumber") buildingNumber: String
    ): Single<List<MAI>>

    @GET("building")
    fun floor(
        @Header("Authorization ") accessToken: String,
        @Query("buildingNumber") buildingNumber: String, @Query("floor") floor: String
    ): Single<List<MAI>>

    @POST("register")
    fun register(
        @Header("Authorization ") accessToken: String,
        @Body body: Any?
    )
}