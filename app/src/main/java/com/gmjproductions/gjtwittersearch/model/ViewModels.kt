package com.gmjproductions.gjtwittersearch.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.twitter.sdk.android.core.TwitterSession
import com.twitter.sdk.android.core.models.Tweet

/**
 * Created by garyjacobs on 1/18/18.
 */
data class SessionViewModel(var session: TwitterSession? = null) : ViewModel()

data class TweetsViewModel(val void: Unit? = null) : ViewModel() {
    var tweetList: MutableLiveData<List<Tweet>> = object : MutableLiveData<List<Tweet>>() {}
    init {
        tweetList.value = emptyList()
    }
}



