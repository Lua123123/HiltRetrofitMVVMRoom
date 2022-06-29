package com.example.test3.databaseTopic

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.test3.model.Success
import com.example.test3.model.SuccessVocabulary

@Database(entities = [Success::class, SuccessVocabulary::class], version = 1, exportSchema = false)
abstract class DatabaseDB : RoomDatabase() {
    companion object {
        private var DB_INSTANCE: DatabaseDB? = null
        private var DB_NAME: String = "topics.db"

        fun getAppDBInstance(context: Context): com.example.test3.databaseTopic.DatabaseDB {
            if (DB_INSTANCE == null) {
                DB_INSTANCE = Room.databaseBuilder(context.applicationContext, DatabaseDB::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .build()
            }
            return DB_INSTANCE as DatabaseDB
        }
    }

    abstract fun topicDAO(): TopicDAO
    abstract fun vocabularyDAO(): VocabularyDAO
}