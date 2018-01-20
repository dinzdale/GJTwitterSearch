package com.gmjproductions.gjtwittersearch.ui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.gmjproductions.gjtwittersearch.R
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import kotlinx.android.synthetic.main.login_twitter.login_button

/**
 * Created by garyjacobs on 1/19/18.
 */
class SearchTweetsLoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_twitter)
        login_button.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>?) {
                val intent = Intent(this@SearchTweetsLoginActivity, SearchTweetsActivity::class.java)
                startActivity(intent)
            }

            override fun failure(exception: TwitterException) {
                showTweetAlertDialog(exception.message!!,R.string.login_issue_title)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // forward result to button
        login_button.onActivityResult(requestCode, resultCode, data)
        // remove this activity
        finish()
    }

}