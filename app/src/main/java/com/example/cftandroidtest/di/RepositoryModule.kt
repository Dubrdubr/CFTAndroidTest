package com.example.cftandroidtest.di

import com.example.cftandroidtest.data.repository.BinRepositoryImpl
import com.example.cftandroidtest.domain.repository.BinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindBinRepository(
        binRepositoryImpl: BinRepositoryImpl
    ): BinRepository
}