package com.gardner.adam_gardner_rm_interview.di

import com.gardner.adam_gardner_rm_interview.data.GameDataSource
import com.gardner.adam_gardner_rm_interview.data.GameRepository
import com.gardner.adam_gardner_rm_interview.data.GameRepositoryImpl
import com.gardner.adam_gardner_rm_interview.data.remote.GameApi
import com.gardner.adam_gardner_rm_interview.utils.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    
    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }
    
    @Provides
    @Singleton
    fun provideGameApi(moshi: Moshi): GameApi {
        return Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(Constants.BASE_URL)
            .build()
            .create(GameApi::class.java)
    }
    
    @Provides
    fun provideGameRepository(dataSource: GameDataSource): GameRepository {
        return GameRepositoryImpl(dataSource)
    }
}