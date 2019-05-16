package com.comfortment.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.comfortment.data.model.AuthEntity
import io.reactivex.Single

@Dao
interface AuthDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(authEntity: AuthEntity)

    @Query("SELECT * FROM auth")
    fun get() : Single<AuthEntity>
}