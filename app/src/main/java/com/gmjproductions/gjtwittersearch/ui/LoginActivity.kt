package com.gmjproductions.gjtwittersearch.ui

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
<<<<<<< HEAD
import android.widget.Toast
=======
>>>>>>> 87d7db6f0b3445a642258af322f7b8a363555189
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
<<<<<<< HEAD

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_twitter)
        login_button.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>?) {
                Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_LONG).show()
                val intent = Intent(this@LoginActivity, SearchTweetsActivity::class.java)
=======
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(R.layout.login_twitter)
        login_button.callback = object : Callback<TwitterSession>() {
            override fun success(result: Result<TwitterSession>?) {
                val intent = Intent(this@LoginActivity,SearchTweetsActivity::class.java)
>>>>>>> 87d7db6f0b3445a642258af322f7b8a363555189
                startActivity(intent)
            }

            override fun failure(exception: TwitterException?) {
<<<<<<< HEAD
                Toast.makeText(this@LoginActivity, "Failed to login: ${exception!!.message}", Toast.LENGTH_LONG).show()
=======
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
>>>>>>> 87d7db6f0b3445a642258af322f7b8a363555189
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // forward result to button
        login_button.onActivityResult(requestCode, resultCode, data)
<<<<<<< HEAD
        finish()
    }

=======
    }
>>>>>>> 87d7db6f0b3445a642258af322f7b8a363555189
}