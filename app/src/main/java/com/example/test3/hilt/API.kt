package com.example.test3.hilt

import com.example.test3.model.Topic
import com.example.test3.model.Vocabulary
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface API {
    @FormUrlEncoded
    @POST("topic/getAllTopic")
    fun getTopics(@Field("user_create") user_create: Int): Call<Topic?>?

    //VOCABULARY
    @FormUrlEncoded
    @POST("vocabulary/getVocabulary")
    fun getVocabulary(
        @Field("user_create") user_create: Int,
        @Field("search") search: String?,
        @Field("page") page: String?
    ): Call<Vocabulary?>?
}
