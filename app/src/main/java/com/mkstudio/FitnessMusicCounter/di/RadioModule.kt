package com.mkstudio.FitnessMusicCounter.di

import android.content.Context
import com.mkstudio.FitnessMusicCounter.radio.RadioServiceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Hilt module for radio service

@InstallIn(SingletonComponent::class)
@Module
object CreateRadioModule {
    @Provides
    @Singleton
    fun provideRadioManager(@ApplicationContext appContext: Context): RadioServiceManager {
        return RadioServiceManager(appContext)
    }
}