package com.example.test3.model

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Vocabulary {
    @SerializedName("success")
    @Expose
    var success: List<SuccessVocabulary>? = null
}