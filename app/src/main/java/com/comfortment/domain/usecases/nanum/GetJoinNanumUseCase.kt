package com.comfortment.domain.usecases.nanum

import com.comfortment.domain.model.Nanum
import com.comfortment.domain.repository.nanum.NanumRepository
import com.comfortment.domain.usecases.UseCase
import io.reactivex.Single

class GetJoinNanumUseCase(private val nanumRepository: NanumRepository) :
    UseCase<GetJoinNanumUseCase.Params, Single<List<Nanum>>>() {

    override fun createObservable(params: Params): Single<List<Nanum>> =
        nanumRepository.getJoinNanum(params.accessToken, params.apartmentId)

    override fun onCleared() {}

    data class Params(val accessToken: String, val apartmentId: String)
}