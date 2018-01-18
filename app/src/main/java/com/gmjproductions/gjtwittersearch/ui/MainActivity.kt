package com.gmjproductions.gjtwittersearch.ui

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.gmjproductions.gjtwittersearch.R
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterSession
import kotlinx.android.synthetic.main.activity_main.*
import layout.SearchTweetsFragment

class MainActivity : AppCompatActivity(), ConnectionStatusUpdate {


    val CONNECTED = "CONNECTED"

    private var connectedStatus: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }
        savedInstanceState?.let {
            connectedStatus = savedInstanceState.getBoolean(CONNECTED)
             if (TwitterCore.getInstance().sessionManager == null) {
                 connectedStatus = true
             }
            else {
                 connectedStatus = false
                 fragmentLoader(LoginTwitterFragment(), LoginTwitterFragment.TAG)
             }

        } ?: fragmentLoader(LoginTwitterFragment(), LoginTwitterFragment.TAG)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // forward connection result back to login fragment (and it's button)
        findFragment(LoginTwitterFragment.TAG)?.let {
            it.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(CONNECTED, connectedStatus)
    }

    fun fragmentLoader(fragment: Fragment, tag: String, addToBackStack: Boolean = false) {
        val fragTransaction = supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, fragment, tag)
        if (addToBackStack) {
            fragTransaction.addToBackStack(tag)
        }
        fragTransaction.commit()
    }

    fun findFragment(tag: String): Fragment? = supportFragmentManager
            .findFragmentByTag(tag)


    override fun connectionSuccess(status: Boolean) {
        connectedStatus = status
        if (connectedStatus) {
            fragmentLoader(SearchTweetsFragment(), SearchTweetsFragment.TAG)
        }
    }
}
