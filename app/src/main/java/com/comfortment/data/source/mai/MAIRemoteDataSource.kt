package com.comfortment.data.source.mai

import com.comfortment.data.remote.api.MaiAPI
import com.comfortment.domain.model.MAI
import com.comfortment.presentation.rx.AppSchedulerProvider
import io.reactivex.Observable
import retrofit2.Response
import javax.inject.Inject

class MAIRemoteDataSource @Inject constructor(
    private val maiAPI: MaiAPI,
    private val appSchedulerProvider: AppSchedulerProvider
) {

    fun loadMyAI(accessToken: String, id: String) =
        maiAPI.apartment("Bearer $accessToken", id)
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())

    fun buildingAI(accessToken: String, buildingNumber: Int) =
        maiAPI.building("Bearer $accessToken", buildingNumber.toString())
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())

    fun floorAI(accessToken: String, buildingNumber: Int, floor: Int) =
        maiAPI.floor("Bearer $accessToken", buildingNumber.toString(), floor.toString())
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())

    fun registerAI(accessToken: String, mai: MAI): Observable<Response<Unit>> =
        maiAPI.register("Bearer $accessToken", mai)
            .subscribeOn(appSchedulerProvider.io())
            .observeOn(appSchedulerProvider.ui())
}