package com.gmjproductions.gjtwittersearch.ui

import android.app.Application
import com.twitter.sdk.android.core.Twitter

/**
 * Created by garyjacobs on 1/17/18.
 */
class SearchTweetsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Twitter.initialize(this)
    }
}