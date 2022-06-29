package com.example.test3.adapter

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.speech.tts.TextToSpeech
import android.util.Log
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.test3.R
import com.example.test3.model.SuccessVocabulary
import com.example.test3.model.SuccessVocabularyyy
import java.util.*

class ListVocabularyAdapter(private var context: Context?) :
    RecyclerView.Adapter<ListVocabularyAdapter.ViewHolder>() {
    private var mListVocabulary = mutableListOf<SuccessVocabulary>()
    private var mListVocabularyOld = mutableListOf<SuccessVocabulary>()
    private lateinit var mTTS: TextToSpeech

    fun setData(mListVocabulary: MutableList<SuccessVocabulary> = mutableListOf()) {
        this.mListVocabulary = mListVocabulary
        mListVocabularyOld = mListVocabulary
        notifyDataSetChanged()
    }

    fun reload(mListVocabulary: List<SuccessVocabulary>?) {
        this.mListVocabulary.clear()
        this.mListVocabulary.addAll(mListVocabulary!!)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_vocabulary_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (true) {
            holder.tvWord.text = mListVocabulary[position].word
            holder.tvMean.text = mListVocabulary[position].mean
            holder.tvExample.text = mListVocabulary[position].example
            val word = mListVocabulary[position].word
            val mean = mListVocabulary[position].mean

            holder.img_detail.setOnClickListener {
            }

            holder.layout_item.setOnClickListener {
            }

        }
    }

    override fun getItemCount(): Int {
        return if (mListVocabulary != null) {
            mListVocabulary.size
        } else 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWord: TextView
        val tvMean: TextView
        val tvExample: TextView
        val img_detail: ImageView
        val layout_item: LinearLayout

        init {
            tvWord = itemView.findViewById(R.id.word)
            tvMean = itemView.findViewById(R.id.mean)
            tvExample = itemView.findViewById(R.id.example)
            img_detail = itemView.findViewById(R.id.img_detail)
            layout_item = itemView.findViewById(R.id.layout_item)
        }
    }
}