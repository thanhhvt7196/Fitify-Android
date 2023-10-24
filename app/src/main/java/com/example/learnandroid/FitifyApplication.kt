package com.example.learnandroid

import android.content.Context
import androidx.multidex.MultiDexApplication
import com.example.learnandroid.data.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FitifyApplication : MultiDexApplication() {
    companion object {
        lateinit var mContext: Context
    }

    // same with willFinishLaunchingWithOptions
    override fun onCreate() {
        super.onCreate()
        mContext=this
        startKoin {
            androidContext(this@FitifyApplication)
            modules(viewModelModule)
        }
    }
}