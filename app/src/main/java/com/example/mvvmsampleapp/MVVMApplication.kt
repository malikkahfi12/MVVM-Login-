package com.example.mvvmsampleapp

import android.app.Application
import com.example.mvvmsampleapp.data.db.AppDatabase
import com.example.mvvmsampleapp.data.network.MyApi
import com.example.mvvmsampleapp.data.network.NetworkConnectionInterceptor
import com.example.mvvmsampleapp.data.preferences.PreferenceProvider
import com.example.mvvmsampleapp.data.repository.QuotesRepository
import com.example.mvvmsampleapp.data.repository.UserRepository
import com.example.mvvmsampleapp.ui.auth.AuthViewModelFactory
import com.example.mvvmsampleapp.ui.home.profile.ProfileViewModelFactory
import com.example.mvvmsampleapp.ui.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {

//        val networkConnectionInterceptor = NetworkConnectionInterceptor(this)
//        val api = MyApi(networkConnectionInterceptor)
//        val db = AppDatabase(this)
//        val repository = UserRepository(api, db)
//        val factory = AuthViewModelFactory(repository)

        import(androidXModule(this@MVVMApplication))
        bind() from  singleton {NetworkConnectionInterceptor(instance())}
        bind() from  singleton { MyApi(instance()) }
        bind() from  singleton  {AppDatabase(instance()) }
        bind() from  singleton { PreferenceProvider(instance()) }
        bind() from  singleton { UserRepository(instance(), instance()) }
        bind() from  singleton { QuotesRepository(instance(), instance(), instance()) }
        bind() from  provider { AuthViewModelFactory(instance()) }
        bind() from  provider { ProfileViewModelFactory(instance()) }
        bind() from  provider { QuotesViewModelFactory(instance()) }
    }
}
