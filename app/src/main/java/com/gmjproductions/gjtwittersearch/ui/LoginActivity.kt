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
class LoginActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_twitter)
        login_button.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>?) {
                Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_LONG).show()
                val intent = Intent(this@LoginActivity, SearchTweetsActivity::class.java)
                startActivity(intent)
            }

            override fun failure(exception: TwitterException?) {

                Toast.makeText(this@LoginActivity, "Failed to login: ${exception!!.message}", Toast.LENGTH_LONG).show()
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