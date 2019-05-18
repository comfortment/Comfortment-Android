package com.comfortment.domain.repository.mai

import com.comfortment.domain.model.MAI
import com.comfortment.domain.model.MyMAI
import io.reactivex.Maybe
import io.reactivex.Single

interface MyMAIRepository {
    fun bringAI(): Maybe<MyMAI>
    fun saveAI(myMAI: MyMAI)
    fun loadAI(id: String): Single<MAI>
}