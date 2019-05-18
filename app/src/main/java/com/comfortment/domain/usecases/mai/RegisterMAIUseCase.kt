package com.comfortment.domain.usecases.mai

import com.comfortment.domain.model.MAI
import com.comfortment.domain.repository.mai.MAIRepository
import com.comfortment.domain.usecases.UseCase
import io.reactivex.Observable
import retrofit2.Response

class RegisterMAIUseCase(private val maiRepository: MAIRepository) : UseCase<RegisterMAIUseCase.Params, Observable<Response<Any>>>() {

    override fun createObservable(params: Params): Observable<Response<Any>> =
        maiRepository.registerAI(
            MAI(
                params.id,
                params.buildingNumber,
                params.roomNumber,
                params.name,
                params.phoneNumber,
                params.disturbTimeRange,
                params.acceptedDecibel,
                params.hateNoiseDescription,
                params.hateSmellDescription,
                params.etc,
                params.role
            )
        )

    override fun onCleared() {}

    class Params(
        val id: String,
        val buildingNumber: Int,
        val roomNumber: Int,
        val name: String,
        val phoneNumber: String,
        val disturbTimeRange: ArrayList<Pair<Int, Int>>,
        val acceptedDecibel: Int,
        val hateNoiseDescription: String,
        val hateSmellDescription: String,
        val etc: String,
        val role: String
    )
}