package com.comfortment.data.remote.api

import com.comfortment.data.model.auth.AuthEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface AuthAPI {

    @GET("auth")
    @Headers("Content-Type: application/json")
    fun request(@Header("Authorization") authorization : String) : Single<AuthEntity>

    @GET("refresh")
    @Headers("Content-Type: application/json")
    fun refresh(@Header("Authorization") authorization : String) : Single<AuthEntity>
 }