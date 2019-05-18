package com.comfortment.domain.repository.mai

import com.comfortment.domain.model.MAI
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Response

interface MAIRepository {
    fun getBuildingAI(buildingNumber: Int): Single<List<MAI>>
    fun getFloorAI(buildingNumber: Int, floor: Int): Single<List<MAI>>
    fun registerAI(mai: MAI): Observable<Response<Any>>
}