package com.gmjproductions.gjtwittersearch.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.gmjproductions.gjtwittersearch.R
import kotlinx.android.synthetic.main.login_twitter.*
import layout.SearchTweetsFragment

/**
 * Created by garyjacobs on 1/19/18.
 */
class SearchTweetsActivity : AppCompatActivity() {

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

    override fun onResume() {
        super.onResume()
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(CONFIGCHANGED, true)
    }

}