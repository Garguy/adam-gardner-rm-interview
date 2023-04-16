package com.gardner.adam_gardner_rm_interview.di

import com.gardner.adam_gardner_rm_interview.data.GameDataSource
import com.gardner.adam_gardner_rm_interview.data.remote.GameApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object GameModule {
    
    @Provides
    fun provideGameData(api: GameApi): GameDataSource {
        return GameDataSource(api)
    }
}