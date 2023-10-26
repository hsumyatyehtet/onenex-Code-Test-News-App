package com.example.onenex_code_test_news_app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Hsu Myat Ye Htet on 25/10/2023 10:59 PM
 */
@HiltAndroidApp
class NewsAppApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}