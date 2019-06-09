package com.comfortment.data.remote.api

import com.comfortment.data.model.mai.MAIEntity
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.*

interface MaiAPI {

    @GET("apartment")
    @Headers("Content-Type: application/json")
    fun apartment(
        @Header("Authorization") accessToken: String,
        @Query("id") id: String
    ): Single<MAIEntity>

    @GET("building")
    @Headers("Content-Type: application/json")
    fun building(
        @Header("Authorization") accessToken: String,
        @Query("buildingNumber") buildingNumber: String
    ): Single<List<MAIEntity>>

    @GET("floor")
    @Headers("Content-Type: application/json")
    fun floor(
        @Header("Authorization") accessToken: String,
        @Query("buildingNumber") buildingNumber: String,
        @Query("floor") floor: String
    ): Single<List<MAIEntity>>

    @POST("register")
    @Headers("Content-Type: application/json")
    fun register(
        @Header("Authorization") accessToken: String,
        @Body body: Any
    ): Observable<Response<Unit>>

}