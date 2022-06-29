package com.example.test3.hilt

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.test3.databaseTopic.TopicDAO
import com.example.test3.databaseTopic.VocabularyDAO
import com.example.test3.model.Success
import com.example.test3.model.SuccessVocabulary
import com.example.test3.model.Topic
import com.example.test3.model.Vocabulary
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class RetroRepository @Inject constructor(private val api: API, private val topicDAO: TopicDAO, private val vocabularyDAO: VocabularyDAO) { //, private val vocabularyDAO: VocabularyDAO
    private var context: Context? = null
    private var postsList: MutableList<Success> = ArrayList()

    fun conText(context: Context, postsList: MutableList<Success> = ArrayList()) {
        this.context = context
        this.postsList = postsList
    }

    fun getAllRecords(): LiveData<List<Success>> {
        return topicDAO.getListTopic()
    }

    fun insertRecord(success: Success) {
        return topicDAO.insertTopic(success)
    }

    fun getAllVocabulary(): LiveData<MutableList<SuccessVocabulary>> {
        return vocabularyDAO.getListVocabulary()
    }

    fun insertVocabulary(successVocabulary: SuccessVocabulary) {
        return vocabularyDAO.insertVocabulary(successVocabulary)
    }

    //get the data from api
    fun makeApiCall(query: Int) {
        val call: Call<Topic?>? = api.getTopics(query)
        call?.enqueue(object : Callback<Topic?> {
            override fun onResponse(call: Call<Topic?>, response: Response<Topic?>) {
                if (response.isSuccessful) {
                    response.body()?.success?.forEach {
                        insertRecord(it)
                    }
                }
            }

            override fun onFailure(call: Call<Topic?>, t: Throwable) {
            }
        })
    }

    fun makeApiCallVocabulary(user_create: Int, search: String, page: String) {
        val call: Call<Vocabulary?>? = api.getVocabulary(user_create, search, page)
        call?.enqueue(object : Callback<Vocabulary?> {
            override fun onResponse(call: Call<Vocabulary?>, response: Response<Vocabulary?>) {
                if (response.isSuccessful) {
                    response.body()?.success?.forEach {
                        insertVocabulary(it)
                    }
                }
            }

            override fun onFailure(call: Call<Vocabulary?>, t: Throwable) {
            }
        })
    }

}