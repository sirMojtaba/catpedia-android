package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.BreedDao
import com.example.data.local.entity.BreedEntity

@Database(entities = [BreedEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun breedDao(): BreedDao
}