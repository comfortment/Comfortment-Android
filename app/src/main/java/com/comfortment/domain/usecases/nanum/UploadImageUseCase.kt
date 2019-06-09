package com.comfortment.domain.usecases.nanum

import com.comfortment.domain.repository.nanum.NanumRepository
import com.comfortment.domain.usecases.UseCase
import io.reactivex.Single

class UploadImageUseCase(private val nanumRepository: NanumRepository) :
    UseCase<UploadImageUseCase.Params, Single<String>>() {

    override fun createObservable(params: Params): Single<String> =
        nanumRepository.uploadImage(params.accessToken, params.imageUrl)

    override fun onCleared() {}

    data class Params(val accessToken: String, val imageUrl: String)
}