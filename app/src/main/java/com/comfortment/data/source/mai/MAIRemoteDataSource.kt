package com.comfortment.data.source.mai

import com.comfortment.data.model.mai.MAIEntity
import com.comfortment.data.remote.api.MaiAPI
import com.comfortment.data.source.auth.AuthLocalDataSource
import com.google.gson.Gson
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class MAIRemoteDataSource @Inject constructor(
    private val maiAPI: MaiAPI,
    authLocalDataSource: AuthLocalDataSource
) {

    private val accessToken = authLocalDataSource.getAuth().blockingGet().accessToken

    fun loadMyAI(id: String) =
        maiAPI.apartment("Bearer $accessToken", id)

    fun buildingAI(buildingNumber: Int) =
        maiAPI.building("Bearer $accessToken", buildingNumber.toString())

    fun floorAI(buildingNumber: Int, floor: Int) =
        maiAPI.floor("Bearer $accessToken", buildingNumber.toString(), floor.toString())

    fun registerAI(maiEntity: MAIEntity): Observable<Response<Any>> =
        maiAPI.register("Bearer $accessToken", Gson().toJson(maiEntity))
}