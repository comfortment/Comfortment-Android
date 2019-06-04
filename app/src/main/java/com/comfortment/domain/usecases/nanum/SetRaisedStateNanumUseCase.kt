package com.comfortment.domain.usecases.nanum

import com.comfortment.domain.repository.nanum.NanumRepository
import com.comfortment.domain.usecases.UseCase
import io.reactivex.Single
import retrofit2.Response

class SetRaisedStateNanumUseCase(private val nanumRepository: NanumRepository) :
    UseCase<SetRaisedStateNanumUseCase.Params, Single<Response<Unit>>>() {

    override fun createObservable(params: Params): Single<Response<Unit>> =
        nanumRepository.setRaisedState(params.accessToken, params.apartmentId, params.nanumId)

    override fun onCleared() {}

    data class Params(val accessToken: String, val apartmentId: String, val nanumId: String)
}