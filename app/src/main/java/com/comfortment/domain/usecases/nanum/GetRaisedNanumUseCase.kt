package com.comfortment.domain.usecases.nanum

import com.comfortment.domain.model.Nanum
import com.comfortment.domain.repository.nanum.NanumRepository
import com.comfortment.domain.usecases.UseCase
import io.reactivex.Single

class GetRaisedNanumUseCase(private val nanumRepository: NanumRepository) :
    UseCase<GetRaisedNanumUseCase.Params, Single<List<Nanum>>>() {

    override fun createObservable(params: Params): Single<List<Nanum>> =
        nanumRepository.getRaisedNanum(params.accessToken, params.apartmentId)

    override fun onCleared() {}

    data class Params(val accessToken: String, val apartmentId: String)
}