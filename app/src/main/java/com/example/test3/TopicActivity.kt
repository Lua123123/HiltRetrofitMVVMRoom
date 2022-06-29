package com.example.test3

import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.test3.adapter.ListTopicAdapter
import com.example.test3.databinding.ActivityTopicBinding
import com.example.test3.extensions.launchActivity
import com.example.test3.extensions.toast
import com.example.test3.model.Success
import com.example.test3.viewmodel.ViewModelApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopicActivity : AppCompatActivity() {
    private lateinit var listTopicAdapter: ListTopicAdapter
    private lateinit var binding: ActivityTopicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_topic)
        supportActionBar?.hide()

        binding.addTopicFab.setOnClickListener {
            launchActivity(VocabularyActivity::class.java)
        }

        initViewModel()
        initMainViewModel()
    }

    private fun initViewModel() {
        binding.topicRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@TopicActivity)
            addItemDecoration(DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL))
            listTopicAdapter = ListTopicAdapter()
            adapter = listTopicAdapter
        }
    }

    private fun initMainViewModel() {
        val viewModelApp: ViewModelApp by viewModels()
        viewModelApp.getAllTopic().observe(this, Observer<List<Success>> {
            listTopicAdapter.setData(it)
            listTopicAdapter.notifyDataSetChanged()
        })

        if (haveNetwork()) {
            toast("CONNECT INTERNET SUCCESS")
            viewModelApp.makeApiCall()
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