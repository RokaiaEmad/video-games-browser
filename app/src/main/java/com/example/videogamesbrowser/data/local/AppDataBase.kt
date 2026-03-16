package com.example.videogamesbrowser.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [GameEntity::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun gameDao(): GameDao
}