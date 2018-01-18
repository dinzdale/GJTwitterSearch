package com.gmjproductions.gjtwittersearch.ui

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gmjproductions.gjtwittersearch.R
import com.gmjproductions.gjtwittersearch.model.SessionViewModel
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.models.Tweet
import com.twitter.sdk.android.tweetui.TweetView
import kotlinx.android.synthetic.main.login_twitter.*
import kotlinx.android.synthetic.main.search_tweets.*

/**
 * Created by garyjacobs on 1/17/18.
 */
class LoginTwitterFragment : Fragment() {
    companion object {
        @JvmStatic
        val TAG = LoginTwitterFragment::class.java.simpleName
    }

    lateinit var myactivity: MainActivity
    lateinit var sessionViewModel: SessionViewModel

    var isLoggedIn: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        savedInstanceState?.let {

        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_twitter, null)
    }


    override fun onAttach(context: Context?) {
        super.onAttach(context)
        myactivity = activity as MainActivity
        sessionViewModel = ViewModelProviders.of(myactivity).get(SessionViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button.callback = object : Callback<TwitterSession>() {

            override fun success(result: Result<TwitterSession>) {
                Toast.makeText(context, "Twitter Login: Success", Toast.LENGTH_LONG).show()
                // save the successful session
                sessionViewModel.session = result.data
                // make the active session
                TwitterCore.getInstance().sessionManager.activeSession = result.data
                // notify parent activity of succesful authentication
                myactivity.connectionSuccess(true)
            }

            override fun failure(exception: TwitterException?) {
                Toast.makeText(context, "Twitter Login: Failure: ${exception!!.message}", Toast.LENGTH_LONG).show()
                myactivity.connectionSuccess(false)
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        login_button.onActivityResult(requestCode, resultCode, data)

    }

}