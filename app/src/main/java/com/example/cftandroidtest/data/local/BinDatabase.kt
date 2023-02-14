package com.example.cftandroidtest.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [BinInfoEntity::class],
    version = 3
)
@TypeConverters(value = [Converters::class])
abstract class BinDatabase : RoomDatabase() {

    abstract fun binInfoDao(): BinInfoDao
}