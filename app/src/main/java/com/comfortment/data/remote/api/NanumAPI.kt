package com.comfortment.data.remote.api

import com.comfortment.data.model.nanum.NanumEntity
import io.reactivex.Single
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface NanumAPI {

    @GET("nanum")
    fun getNanum(
        @Header("Authorization") accessToken: String,
        @Header("type") type: String,
        @Header("expiry") expiry: String
    ): Single<List<NanumEntity>>

    @POST("nanum/{apartmentId}/raised")
    fun postNanum(
        @Header("Authorization") accessToken: String,
        @Path("apartmentId") apartmentId: String,
        @Body body: Any
    ): Single<Response<Unit>>

    @PATCH("nanum/{apartmentId}/raised/{nanumId}")
    fun editNanum(
        @Header("Authorization") accessToken: String,
        @Path("apartmentId") apartmentId: String,
        @Path("nanumId") nanumId: String,
        @Body body: Any
    ): Single<Response<Unit>>

    @POST("nanum/image")
    fun uploadImage(
        @Header("Authorization") accessToken: String,
        @Part("file\"; filename=\"photo.png") file: RequestBody
    ): Single<String>

    @GET("nanum/{nanumId}")
    fun nanumDetail(
        @Header("Authorization") accessToken: String,
        @Path("nanumId") nanumId: String
    ): Single<NanumEntity>

    @PATCH("nanum/{apartmentId}/joined/{nanumId}")
    fun joinNanum(
        @Header("Authorization") accessToken: String,
        @Path("apartmentId") apartmentId: String,
        @Path("nanumId") nanumId: String
    ): Single<Response<Unit>>

    @GET("nanum/{apartmentId}/joined")
    fun getJoinNanum(
        @Header("Authorization") accessToken: String,
        @Path("apartmentId") apartmentId: String
    ): Single<List<NanumEntity>>

    @PATCH("nanum/{apartmentId}/raised/{nanumId}/state")
    fun setRaisedStateNanum(
        @Header("Authorization") accessToken: String,
        @Path("apartmentId") apartmentId: String,
        @Path("nanumId") nanumId: String,
        @Header("currentState") currentState: String
    ): Single<Response<Unit>>

    @GET("nanum/{apartmentId}/raised")
    fun getRaisedNanum(
        @Header("Authorization") accessToken: String,
        @Path("apartmentId") apartmentId: String
    ): Single<List<NanumEntity>>

    @PATCH("nanum/{apartmentId}/stared/{nanumId}")
    fun setStarNanum(
        @Header("Authorization") accessToken: String,
        @Path("apartmentId") apartmentId: String,
        @Path("nanumId") nanumId: String
    ): Single<Response<Unit>>

    @GET("nanum/{apartmentId}/stared")
    fun getStarNanum(
        @Header("Authorization") accessToken: String,
        @Path("apartmentId") apartmentId: String
    ): Single<List<NanumEntity>>

}