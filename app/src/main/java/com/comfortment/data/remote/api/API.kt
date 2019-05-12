package com.comfortment.data.remote.api

import com.comfortment.domain.model.Auth
import retrofit2.Call
import retrofit2.http.*

interface API {

    @POST("auth")
    @Headers("Content-Type: application/json")
    fun oauth(@Header("Authorization") authorization : String) : Call<Auth>

    @POST("emit/noise")
    fun sendNotify()
 }