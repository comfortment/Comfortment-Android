package com.comfortment.data.remote.api

import com.comfortment.data.model.nanum.NanumDetail
import com.comfortment.data.model.nanum.NanumList
import io.reactivex.Single
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface NanumAPI {

    @GET("nanum")
    fun getNanum(
        @Header("Authorization") accessToken: String,
        @Query("type") type: String,
        @Query("expiry") expiry: String
    ): Single<NanumList>

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

    @Multipart
    @POST("nanum/image")
    fun uploadImage(
        @Header("Authorization") accessToken: String,
        @Part file: MultipartBody.Part
    ): Single<ResponseBody>

    @GET("nanum/{nanumId}")
    fun nanumDetail(
        @Header("Authorization") accessToken: String,
        @Path("nanumId") nanumId: String
    ): Single<NanumDetail>

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
    ): Single<NanumList>

    @PATCH("nanum/{apartmentId}/raised/{nanumId}/state")
    fun setRaisedStateNanum(
        @Header("Authorization") accessToken: String,
        @Path("apartmentId") apartmentId: String,
        @Path("nanumId") nanumId: String,
        @Body currentState: Any
    ): Single<Response<Unit>>

    @GET("nanum/{apartmentId}/raised")
    fun getRaisedNanum(
        @Header("Authorization") accessToken: String,
        @Path("apartmentId") apartmentId: String
    ): Single<NanumList>

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
    ): Single<NanumList>

}