package com.comfortment.data.remote.api

import com.comfortment.data.model.AuthEntity
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface AuthAPI {

    @GET("auth")
    @Headers("Content-Type: application/json")
    fun oauth(@Header("Authorization") authorization : String) : Single<AuthEntity>
 }