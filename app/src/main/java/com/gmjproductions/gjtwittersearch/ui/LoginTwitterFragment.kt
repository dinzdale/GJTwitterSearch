package com.gmjproductions.gjtwittersearch.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gmjproductions.gjtwittersearch.R
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import kotlinx.android.synthetic.main.login_twitter.*

/**
 * Created by garyjacobs on 1/17/18.
 */
class LoginTwitterFragment : Fragment() {
    lateinit var myactivity: FragmentActivity

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
        myactivity = activity!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_button.callback = object : Callback<TwitterSession>() {

            override fun success(result: Result<TwitterSession>?) {
                Toast.makeText(context,"Twitter Login: Success",Toast.LENGTH_LONG).show()
            }

            override fun failure(exception: TwitterException?) {
                Toast.makeText(context,"Twitter Login: Failure: ${exception!!.message}",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        login_button.onActivityResult(requestCode,resultCode,data)
    }
}