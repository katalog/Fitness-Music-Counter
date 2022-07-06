package com.mkstudio.FitnessMusicCounter.di

import android.content.Context
import com.mkstudio.FitnessMusicCounter.repo.LocalPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Hilt module for local preferences

@InstallIn(SingletonComponent::class)
@Module
object CreateLocalPreferencesModule {
    @Provides
    @Singleton
    fun provideLocalPreferences(@ApplicationContext appContext: Context): LocalPreferences {
        return LocalPreferences(appContext)
    }
}
