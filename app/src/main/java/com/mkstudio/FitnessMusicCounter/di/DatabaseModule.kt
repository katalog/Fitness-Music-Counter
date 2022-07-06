package com.mkstudio.FitnessMusicCounter.di

import android.content.Context
import androidx.room.Room
import com.mkstudio.FitnessMusicCounter.repo.db.WorkoutRecordDao
import com.mkstudio.FitnessMusicCounter.repo.db.WorkoutRecordDatabase
import com.mkstudio.FitnessMusicCounter.repo.db.WorkoutRecordSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Hilt module for database

@InstallIn(SingletonComponent::class)
@Module
object CreateDatabaseModule {
    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext appContext: Context): WorkoutRecordDatabase {
        return Room.databaseBuilder(
            appContext,
            WorkoutRecordDatabase::class.java,
            "workoutrecord-database.db"
        ).allowMainThreadQueries().build()
    }
}

@InstallIn(SingletonComponent::class)
@Module
object DatabaseAccessModule {
    @Singleton
    @Provides
    fun provideUserDao(database: WorkoutRecordDatabase): WorkoutRecordDao {
        return database.workoutRecordDao()
    }

    @Singleton
    @Provides
    fun provideDatabaseSource(dao: WorkoutRecordDao): WorkoutRecordSource {
        return WorkoutRecordSource(dao)
    }
}