package com.gmjproductions.gjtwittersearch.ui

import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.gmjproductions.gjtwittersearch.R
import layout.SearchTweetsFragment

/**
 * Created by garyjacobs on 1/19/18.
 */
class SearchTweetsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.tweet_search)

        // load search fragment
        fragmentLoader(SearchTweetsFragment(), R.id.tweet_search_container, SearchTweetsFragment.TAG)

        // load tweet results fragment
        fragmentLoader(SearchTweetsResultsFragment(), R.id.tweet_results_container, SearchTweetsResultsFragment.TAG)
    }
}