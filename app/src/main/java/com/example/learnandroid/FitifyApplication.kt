package com.example.learnandroid

import androidx.multidex.MultiDexApplication
import com.example.learnandroid.data.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FitifyApplication : MultiDexApplication() {

    // same with willFinishLaunchingWithOptions
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@FitifyApplication)
            modules(viewModelModule)
        }
    }
}