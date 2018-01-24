package com.gmjproductions.gjtwittersearch.model

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.gmjproductions.gjtwittersearch.R
import com.gmjproductions.gjtwittersearch.ui.showTweetAlertDialog
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.models.Search
import com.twitter.sdk.android.core.models.Tweet

/**
 * Created by garyjacobs on 1/18/18.
 */
data class TweetsViewModel(val void: Unit? = null) : ViewModel() {
    var tweetList: MutableLiveData<List<Tweet>> = object : MutableLiveData<List<Tweet>>() {}

    init {
        tweetList.value = emptyList()
    }

    fun searchTweets(search: String, language: String, count: Int, noResultsCB: (Pair<Int, String?>) -> Unit) {

        val call = TwitterCore.getInstance().apiClient.searchService.tweets(search,
                null,
                language,
                null,
                null,
                count,
                null,
                null,
                null,
                null)

        call.enqueue(object : Callback<Search>() {
            override fun success(result: Result<Search>) {

                val tweetsList = result.data.tweets

                if (tweetsList.size > 0) {
                    // Update view model and trigger twee list update
                    tweetList.value = tweetsList
                }

                noResultsCB.invoke(Pair(tweetsList.size, null))
            }

            override fun failure(exception: TwitterException) {
                noResultsCB.invoke(Pair(-1, exception.message))
            }
        })
    }
}



