package com.example.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.LiveData
import com.example.mvvmsampleapp.data.db.entity.Quote
import kotlinx.coroutines.Deferred


interface QuotesListener {

    fun onSucces(quotes: Deferred<LiveData<List<Quote>>>)

}