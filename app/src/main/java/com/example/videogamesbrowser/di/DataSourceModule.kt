package com.example.videogamesbrowser.di


import com.example.videogamesbrowser.data.datasource.MainDataSource
import com.example.videogamesbrowser.data.datasource.MainDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindMainDataSource(
        mainDataSourceImpl: MainDataSourceImpl
    ): MainDataSource
}