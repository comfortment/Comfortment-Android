package com.comfortment.domain.usecases.nanum

import com.comfortment.domain.model.Nanum
import com.comfortment.domain.repository.nanum.NanumRepository
import com.comfortment.domain.usecases.UseCase
import io.reactivex.Single

class ShowNanumDetailUseCase(private val nanumRepository: NanumRepository) :
    UseCase<ShowNanumDetailUseCase.Params, Single<Nanum>>() {

    override fun createObservable(params: Params): Single<Nanum> =
        nanumRepository.showNanumDetail(params.accessToken, params.nanumId)

    override fun onCleared() {}

    data class Params(val accessToken: String, val nanumId: String)
}