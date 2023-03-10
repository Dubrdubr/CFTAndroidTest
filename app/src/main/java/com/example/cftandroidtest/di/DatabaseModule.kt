package com.example.cftandroidtest.di

import android.app.Application
import androidx.room.Room
import com.example.cftandroidtest.data.local.BinDatabase
import com.example.cftandroidtest.data.local.BinInfoDao
import com.example.cftandroidtest.data.local.Converters
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
        typeConverter: Converters
    ): BinDatabase {
        return Room.databaseBuilder(
            application,
            BinDatabase::class.java,
            "BinDatabase.db"
        ).addTypeConverter(typeConverter).build()
    }

    @Provides
    @Singleton
    fun provideBinInfoDao(
        database: BinDatabase
    ): BinInfoDao {
        return database.binInfoDao()
    }

    @Provides
    @Singleton
    fun provideTypeConverter(
        moshi: Moshi
    ): Converters {
        return Converters(moshi)
    }
}