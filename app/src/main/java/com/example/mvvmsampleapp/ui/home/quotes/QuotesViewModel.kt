package com.example.mvvmsampleapp.ui.home.quotes

import androidx.lifecycle.ViewModel
import com.example.mvvmsampleapp.data.repository.QuotesRepository
import com.example.mvvmsampleapp.util.ApiException
import com.example.mvvmsampleapp.util.NoInternetException
import com.example.mvvmsampleapp.util.lazyDeffered

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeffered {
        try {
            repository.getQuotes()
        } catch (e : ApiException){

        } catch (e: NoInternetException){

        }
    }

}
