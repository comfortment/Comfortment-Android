package com.comfortment.domain.usecases.nanum

import com.comfortment.domain.repository.nanum.NanumRepository
import com.comfortment.domain.usecases.UseCase
import io.reactivex.Single
import retrofit2.Response

class JoinNanumUseCase(private val nanumRepository: NanumRepository) :
    UseCase<JoinNanumUseCase.Params, Single<Response<Unit>>>() {

    override fun createObservable(params: Params): Single<Response<Unit>> =
        nanumRepository.joinNanum(params.accessToken, params.apartmentId, params.nanumId)

    override fun onCleared() {}

    data class Params(val accessToken: String, val apartmentId: String, val nanumId: String)
}