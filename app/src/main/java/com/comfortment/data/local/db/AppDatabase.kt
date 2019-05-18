package com.comfortment.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.comfortment.data.local.db.dao.AuthDao
import com.comfortment.data.local.db.dao.MyMAIDao
import com.comfortment.data.model.auth.AuthEntity
import com.comfortment.data.model.mai.MyMAIEntity

@Database(entities = [AuthEntity::class, MyMAIEntity::class], version = 2, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun authDao() : AuthDao
    abstract fun maiDao() : MyMAIDao
}