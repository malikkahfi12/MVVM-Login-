package com.example.mvvmsampleapp.data.network.response

import androidx.lifecycle.LiveData
import com.example.mvvmsampleapp.data.db.entity.Quote

data class QuotesResponse(
    val isSuccessful:Boolean,
    val quotes:List<Quote>
)