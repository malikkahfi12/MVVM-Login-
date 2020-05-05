package com.example.mvvmsampleapp.ui.home.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.data.db.entity.Quote
import com.example.mvvmsampleapp.databinding.QuotesFragmentBinding
import com.example.mvvmsampleapp.util.*
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.quotes_fragment.*
import kotlinx.coroutines.Deferred
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware, QuotesListener {

    override val kodein by kodein()

    private val factory: QuotesViewModelFactory by instance()
    private lateinit var _viewModel: QuotesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _viewModel = ViewModelProvider(this, factory).get(QuotesViewModel::class.java)
        val binding : QuotesFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.quotes_fragment, container, false)
        binding.viewmodel = _viewModel
        _viewModel.quotesListener = this
        bindUI()
        return binding.root
    }


    private fun bindUI() = Coroutines.main {
        progress_bar_quotes.show()
        try {
            _viewModel.quotes.await().observe(viewLifecycleOwner, Observer {
                initRecyclerView(it.toQuoteItem())
                progress_bar_quotes.hide()
            })
        } catch (e : ApiException){
            progress_bar_quotes.visibility = View.GONE
            layout_connections.visibility = View.VISIBLE
        } catch (e : NoInternetException){
            progress_bar_quotes.visibility = View.GONE
            layout_connections.visibility = View.VISIBLE
        }

    }


    private fun initRecyclerView(toQuoteItem: List<QuoteItem>) {

        val mAdapter = GroupAdapter<ViewHolder>().apply {
            addAll(toQuoteItem)
        }

        recycler_view.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = mAdapter
        }

    }

    private fun List<Quote>.toQuoteItem(): List<QuoteItem> {
        return this.map {
            QuoteItem(it)
        }
    }

    override fun onSucces(quotes: Deferred<LiveData<List<Quote>>>) {
        bindUI()
        context?.toast("Retry")
    }

}
