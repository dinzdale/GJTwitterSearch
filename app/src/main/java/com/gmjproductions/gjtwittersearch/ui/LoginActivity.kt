package com.gmjproductions.gjtwittersearch.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import com.gmjproductions.gjtwittersearch.R
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import kotlinx.android.synthetic.main.login_twitter.login_button

/**
 * Created by garyjacobs on 1/19/18.
 */
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.login_twitter)
        login_button.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>?) {
                val intent = Intent(this@LoginActivity,SearchTweetsActivity::class.java)
                startActivity(intent)
            }

            override fun failure(exception: TwitterException?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // forward result to button
        login_button.onActivityResult(requestCode, resultCode, data)
    }
}