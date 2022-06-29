package com.example.test3.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.test3.hilt.RetroRepository
import com.example.test3.model.Success
import com.example.test3.model.SuccessVocabulary
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ViewModelApp @Inject constructor(private val retroRepository: RetroRepository) :
    ViewModel() {

    fun getAllTopic(): LiveData<List<Success>> {
        return retroRepository.getAllRecords()
    }

    fun makeApiCall() {
        retroRepository.makeApiCall(1)
    }

    fun getAllVocabulary(): LiveData<MutableList<SuccessVocabulary>> {
        return retroRepository.getAllVocabulary()
    }

    fun makeApiCallVocabulary() {
        retroRepository.makeApiCallVocabulary(1, "", "30")
    }
}