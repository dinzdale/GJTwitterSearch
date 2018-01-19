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
<<<<<<< HEAD

    companion object {
        @JvmStatic
        val CONFIGCHANGED = "CONFIGCHANGED"
    }

    var configChange: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.tweet_search)

        savedInstanceState?.let {
            configChange = savedInstanceState.getBoolean(CONFIGCHANGED)
            savedInstanceState.putBoolean(CONFIGCHANGED, false)
        }
        if (!configChange) {

            // load search fragment
            fragmentLoader(SearchTweetsFragment(), R.id.tweet_search_container, SearchTweetsFragment.TAG)

            // load tweet results fragment
            fragmentLoader(SearchTweetsResultsFragment(), R.id.tweet_results_container, SearchTweetsResultsFragment.TAG)
        }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(CONFIGCHANGED, true)
=======
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.tweet_search)

        // load search fragment
        fragmentLoader(SearchTweetsFragment(), R.id.tweet_search_container, SearchTweetsFragment.TAG)

        // load tweet results fragment
        fragmentLoader(SearchTweetsResultsFragment(), R.id.tweet_results_container, SearchTweetsResultsFragment.TAG)
>>>>>>> 87d7db6f0b3445a642258af322f7b8a363555189
    }
}