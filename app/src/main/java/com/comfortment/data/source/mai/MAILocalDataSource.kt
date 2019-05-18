package com.comfortment.data.source.mai

import com.comfortment.data.local.db.AppDatabase
import com.comfortment.data.model.mai.MyMAIEntity
import io.reactivex.Maybe
import javax.inject.Inject

class MAILocalDataSource @Inject constructor(private val appDatabase: AppDatabase) {

    fun bringMyMAI(): Maybe<MyMAIEntity> = appDatabase.maiDao().get()

    fun saveMyMAI(myMAIEntity: MyMAIEntity) = appDatabase.maiDao().insert(myMAIEntity)

}