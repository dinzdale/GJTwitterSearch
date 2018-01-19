package com.gmjproductions.gjtwittersearch.ui

import android.content.Context
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import com.gmjproductions.gjtwittersearch.R
import java.security.AccessControlContext

/**
 * Created by garyjacobs on 1/19/18.
 */
fun FragmentActivity.fragmentLoader(fragment: Fragment, container:Int, tag: String, addToBackStack: Boolean = false) {
    val fragTransaction = this.supportFragmentManager
            .beginTransaction()
            .replace(container, fragment, tag)
    if (addToBackStack) {
        fragTransaction.addToBackStack(tag)
    }
    fragTransaction.commit()
}

fun FragmentActivity.findFragment(tag: String): Fragment? = supportFragmentManager
        .findFragmentByTag(tag)

fun FragmentActivity.removeFragment(tag: String) {
    val fragmentToRemove = findFragment(tag)
    fragmentToRemove?.let {
        supportFragmentManager
                .beginTransaction()
                .remove(it)
                .commit()
    }
}