package com.example.test3.databaseTopic

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.test3.model.Success
import com.example.test3.model.SuccessVocabulary

@Dao
interface TopicDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTopic(success: Success)

    @Query("SELECT * FROM topics")
    fun getListTopic() : LiveData<List<Success>>

    @Query("DELETE FROM topics")
    fun deleteAllRecords()
}

@Dao
interface VocabularyDAO {

    @Insert
    fun insertVocabulary(successVocabulary: SuccessVocabulary)

    @Query("SELECT * FROM vocabulary")
    fun getListVocabulary() : LiveData<MutableList<SuccessVocabulary>>
}