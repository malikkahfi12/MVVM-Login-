package com.example.mvvmsampleapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvmsampleapp.data.db.AppDatabase
import com.example.mvvmsampleapp.data.db.entity.Quote
import com.example.mvvmsampleapp.data.network.MyApi
import com.example.mvvmsampleapp.data.network.SafeApiRequest
import com.example.mvvmsampleapp.data.preferences.PreferenceProvider
import com.example.mvvmsampleapp.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime


import java.time.temporal.ChronoUnit
private val MINIMUM_INTERVAL = 6
class QuotesRepository(
    private val api:MyApi,
    private val db:AppDatabase,
    private val prefs:PreferenceProvider
) : SafeApiRequest() {
    private val quotes = MutableLiveData<List<Quote>>()
    init {
        quotes.observeForever{
            saveQuotes(it)
        }
    }

    private suspend fun fetchQuote(){
        val lastSavedAt = prefs.getLastSavedAt()
        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))){
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)
        }
    }

    suspend fun getQuotes():LiveData<List<Quote>>{
        return withContext(Dispatchers.IO){
            fetchQuote()
            db.getQuoteDao().getQuotes()
        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime): Boolean{
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }

    private fun saveQuotes(quotes:List<Quote>){
        Coroutines.io {
            prefs.savelastSAvedAt(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quotes)
        }
    }
}