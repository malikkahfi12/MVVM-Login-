package com.example.mvvmsampleapp.ui.home.quotes

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleapp.data.repository.QuotesRepository
import com.example.mvvmsampleapp.util.ApiException
import com.example.mvvmsampleapp.util.NoInternetException
import com.example.mvvmsampleapp.util.lazyDeffered

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {
    var quotesListener: QuotesListener? = null
    val quotes by lazyDeffered {
        repository.getQuotes()
    }

    fun onClickRetry(view: View) {
        quotesListener?.onSucces(quotes)
    }


}
