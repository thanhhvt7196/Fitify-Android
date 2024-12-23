package com.example.fitifyandroid

import androidx.multidex.MultiDexApplication
import com.example.fitifyandroid.data.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FitifyApplication : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FitifyApplication)
            modules(viewModelModule)
        }
    }
}