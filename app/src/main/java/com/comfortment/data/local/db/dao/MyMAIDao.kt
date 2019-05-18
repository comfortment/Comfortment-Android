package com.comfortment.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.comfortment.data.model.mai.MyMAIEntity
import io.reactivex.Maybe

@Dao
interface MyMAIDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(myMAIEntity: MyMAIEntity)

    @Query("SELECT * FROM mymai")
    fun get() : Maybe<MyMAIEntity>
}