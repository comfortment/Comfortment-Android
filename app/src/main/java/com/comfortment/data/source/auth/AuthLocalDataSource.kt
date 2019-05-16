package com.comfortment.data.source.auth

import com.comfortment.data.local.db.AppDatabase
import com.comfortment.data.model.AuthEntity
import io.reactivex.Maybe
import javax.inject.Inject

class AuthLocalDataSource @Inject constructor(private val appDatabase: AppDatabase) {

    fun getAuth(): Maybe<AuthEntity> = appDatabase.authDao().get()

    fun saveAuth(authEntity: AuthEntity) = appDatabase.authDao().insert(authEntity)

}