package com.example.cftandroidtest.di

import com.example.cftandroidtest.data.remote.BinClient
import com.example.cftandroidtest.data.remote.BinService
import com.example.cftandroidtest.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideBinService(
        retrofit: Retrofit
    ): BinService {
        return retrofit.create(BinService::class.java)
    }

    @Provides
    @Singleton
    fun provideBinClient(
        binService: BinService
    ): BinClient {
        return BinClient(binService)
    }
}