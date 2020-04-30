package com.example.mvvmsampleapp.ui.home.quotes

import android.view.View
import androidx.lifecycle.ViewModel
import com.example.mvvmsampleapp.data.repository.QuotesRepository
import com.example.mvvmsampleapp.util.lazyDeffered
import com.example.mvvmsampleapp.util.toast

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeffered {
        repository.getQuotes()
    }


}
