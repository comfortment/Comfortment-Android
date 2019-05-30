package com.comfortment.domain.repository.mai

import com.comfortment.domain.model.MAI
import com.comfortment.domain.model.MyMAI
import io.reactivex.Single

interface MyMAIRepository {
    fun bringAI(): Single<MyMAI>
    fun saveAI(myMAI: MyMAI)
    fun loadAI(accessToken: String, id: String): Single<MAI>
}