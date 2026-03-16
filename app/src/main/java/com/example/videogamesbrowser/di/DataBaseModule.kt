package com.example.videogamesbrowser.di

import android.content.Context
import androidx.room.Room
import com.example.videogamesbrowser.data.local.AppDataBase
import com.example.videogamesbrowser.data.local.GameDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "games_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideGameDao(database: AppDataBase): GameDao {
        return database.gameDao()
    }


}