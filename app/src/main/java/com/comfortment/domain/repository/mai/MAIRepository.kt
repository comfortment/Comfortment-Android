package com.comfortment.domain.repository.mai

import com.comfortment.domain.model.MAI
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

interface MAIRepository {
    fun getBuildingAI(accessToken: String, buildingNumber: Int): Single<List<MAI>>
    fun getFloorAI(accessToken: String, buildingNumber: Int, floor: Int): Single<List<MAI>>
    fun registerAI(accessToken: String, mai: MAI): Observable<Response<Any>>
}