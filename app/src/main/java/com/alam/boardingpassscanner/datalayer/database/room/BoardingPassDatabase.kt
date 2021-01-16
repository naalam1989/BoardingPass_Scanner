package com.alam.boardingpassscanner.datalayer.database.room

import androidx.annotation.Keep
import androidx.room.Database
import androidx.room.RoomDatabase

@Keep
@Database(entities = [BoardingPassEntity::class], version = 1, exportSchema = false)
abstract class BoardingPassDatabase : RoomDatabase() {
    abstract fun boardingPassDao() : BoardingPassDao
}