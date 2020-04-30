package com.example.mvvmsampleapp.ui.home.quotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmsampleapp.R
import com.example.mvvmsampleapp.data.db.entity.Quote
import com.example.mvvmsampleapp.util.Coroutines
import com.example.mvvmsampleapp.util.hide
import com.example.mvvmsampleapp.util.show
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.quotes_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.kodein
import org.kodein.di.generic.instance

class QuotesFragment : Fragment(), KodeinAware {

    override val kodein by kodein()

    private val factory: QuotesViewModelFactory by instance()
    private lateinit var viewModel: QuotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.quotes_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(QuotesViewModel::class.java)

//        Coroutines.main {
//            val quotes = viewModel.quotes.await()
//            quotes.observe(viewLifecycleOwner, Observer {
//                context?.toast(it.size.toString())
//            })
//        }

        bindUI()

    }

    private fun bindUI() = Coroutines.main {
        progress_bar_quotes.show()

        viewModel.quotes.await().observe(viewLifecycleOwner, Observer {
            initRecyclerView(it.toQuoteItem())
            progress_bar_quotes.hide()
        })

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

}
