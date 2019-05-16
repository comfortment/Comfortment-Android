package com.comfortment.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.comfortment.data.local.db.dao.AuthDao
import com.comfortment.data.model.AuthEntity

@Database(entities = [AuthEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun authDao() : AuthDao
}