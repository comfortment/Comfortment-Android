package com.comfortment.domain.usecases.nanum

import com.comfortment.domain.model.Nanum
import com.comfortment.domain.repository.nanum.NanumRepository
import com.comfortment.domain.usecases.UseCase
import io.reactivex.Single

class GetNanumUseCase(private val nanumRepository: NanumRepository) :
    UseCase<GetNanumUseCase.Params, Single<List<Nanum>>>() {

    override fun createObservable(params: Params): Single<List<Nanum>> =
        nanumRepository.getNanum(params.accessToken, params.type, params.expiry)

    override fun onCleared() {}

    data class Params(val accessToken: String, val type: String, val expiry: String)
}