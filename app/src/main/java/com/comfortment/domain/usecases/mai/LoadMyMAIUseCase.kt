package com.comfortment.domain.usecases.mai

import com.comfortment.domain.model.MAI
import com.comfortment.domain.repository.mai.MyMAIRepository
import com.comfortment.domain.usecases.UseCase
import io.reactivex.Single

class LoadMyMAIUseCase(private val myMAIRepository: MyMAIRepository) : UseCase<LoadMyMAIUseCase.Params, Single<MAI>>() {

    override fun createObservable(params: Params): Single<MAI> = myMAIRepository.loadAI(params.id)

    override fun onCleared() {}

    class Params(val id: String)
}