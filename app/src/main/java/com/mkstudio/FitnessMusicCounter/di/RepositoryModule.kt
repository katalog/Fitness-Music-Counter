package com.mkstudio.FitnessMusicCounter.di

import android.content.Context
import com.mkstudio.FitnessMusicCounter.radio.RadioServiceManager
import com.mkstudio.FitnessMusicCounter.repo.LocalPreferences
import com.mkstudio.FitnessMusicCounter.repo.MainRepository
import com.mkstudio.FitnessMusicCounter.repo.db.WorkoutRecordDatabase
import com.mkstudio.FitnessMusicCounter.repo.db.WorkoutRecordSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Hilt module for repository

@InstallIn(SingletonComponent::class)
@Module
object CreateRepositoryModule {
    @Provides
    @Singleton
    fun provideRepositoryManager(db:WorkoutRecordSource, prep:LocalPreferences): MainRepository {
        return MainRepository(db, prep)
    }
}