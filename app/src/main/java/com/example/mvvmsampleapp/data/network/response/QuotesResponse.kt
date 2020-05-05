package com.example.mvvmsampleapp.data.network.response

import com.example.mvvmsampleapp.data.db.entity.Quote

data class QuotesResponse(
    val isSuccessful:Boolean,
    val quotes:List<Quote>
)