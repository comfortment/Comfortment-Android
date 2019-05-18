package com.comfortment.domain.usecases.mai

import com.comfortment.domain.model.MAI
import com.comfortment.domain.repository.mai.MAIRepository
import com.comfortment.domain.usecases.UseCase
import io.reactivex.Single

class MAIUseCase(private val maiRepository: MAIRepository) : UseCase<MAIUseCase.Params, Single<List<MAI>>>() {

    override fun createObservable(params: Params): Single<List<MAI>> =
        if (params.floor == 0) maiRepository.getBuildingAI(params.accessToken, params.buildingNumber)
        else maiRepository.getFloorAI(params.accessToken, params.buildingNumber, params.floor)

    override fun onCleared() {}

    data class Params(
        val accessToken: String,
        val buildingNumber: Int,
        val floor: Int
    ) {
        constructor(accessToken: String, buildingNumber: Int) : this(accessToken, buildingNumber, 0)
    }
}