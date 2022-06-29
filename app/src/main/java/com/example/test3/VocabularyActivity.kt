package com.example.test3

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test3.adapter.ListVocabularyAdapter
import com.example.test3.databinding.ActivityVocabularyBinding
import com.example.test3.extensions.toast
import com.example.test3.model.SuccessVocabulary
import com.example.test3.viewmodel.ViewModelApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VocabularyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityVocabularyBinding
    private lateinit var listVocabularyAdapter: ListVocabularyAdapter
    private lateinit var mListVocabulary: MutableList<SuccessVocabulary>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vocabulary)
        supportActionBar?.hide()

        initViewModel()
        initMainViewModel()
    }

    private fun initViewModel() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@VocabularyActivity)
            addItemDecoration(
                DividerItemDecoration(
                    applicationContext,
                    DividerItemDecoration.VERTICAL
                )
            )
            listVocabularyAdapter = ListVocabularyAdapter(context = this@VocabularyActivity)
            adapter = listVocabularyAdapter
        }
    }

    private fun initMainViewModel() {
        val viewModel: ViewModelApp by viewModels()
        mListVocabulary = ArrayList()
        viewModel.getAllVocabulary().observe(this, Observer<List<SuccessVocabulary>> {
            for (i in 0..72) {
                val successVocabulary = SuccessVocabulary(
                    0, it[i].word,
                    it[i].mean, it[i].example
                )
                Log.d("iii", it.size.toString())
                mListVocabulary.add(successVocabulary)
                listVocabularyAdapter.setData(mListVocabulary)
                binding.recyclerView.adapter = listVocabularyAdapter
            }
        })

        if (haveNetwork()) {
            toast("CONNECT INTERNET SUCCESS")
            viewModel.makeApiCallVocabulary()
        }
        if (!haveNetwork()) {
            toast("DON'T HAVE INTERNET")
        }
    }

    private fun haveNetwork(): Boolean {
        var haveWIFI = false
        var haveMobileData = false
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.allNetworkInfo
        for (info in networkInfo) {
            if (info.typeName.equals("WIFI", ignoreCase = true)) if (info.isConnected) haveWIFI =
                true
            if (info.typeName.equals(
                    "MOBILE",
                    ignoreCase = true
                )
            ) if (info.isConnected) haveMobileData = true
        }
        return haveWIFI || haveMobileData
    }
}