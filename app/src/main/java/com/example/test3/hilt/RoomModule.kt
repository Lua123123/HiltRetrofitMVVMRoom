package com.example.test3.hilt

import android.content.Context
import com.example.test3.databaseTopic.TopicDAO
import com.example.test3.databaseTopic.DatabaseDB
import com.example.test3.databaseTopic.VocabularyDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val DATABASE_NAME = "topics.db"
    val DOMAIN = "http://21.64.10.232/quanlytuvung/public/api/"

    @Provides
    @Singleton
    fun getAppDatabase(@ApplicationContext context: Context): DatabaseDB {
        return DatabaseDB.getAppDBInstance(context)
    }

    @Provides
    @Singleton
    fun getAppDao(databaseDB: DatabaseDB): TopicDAO {
        return databaseDB.topicDAO()
    }

    @Provides
    @Singleton
    fun getAppDaoVocabulary(databaseDB: DatabaseDB): VocabularyDAO {
        return databaseDB.vocabularyDAO()
    }

    @Provides
    @Singleton
    fun getRetroServiceInstance(retrofit: Retrofit): API {
        return retrofit.create(API::class.java)
    }

    @Provides
    @Singleton
    fun getRetroInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}