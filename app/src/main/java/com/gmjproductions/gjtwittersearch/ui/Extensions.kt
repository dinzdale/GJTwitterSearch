package com.gmjproductions.gjtwittersearch.ui

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.gmjproductions.gjtwittersearch.R


/**
 * Created by garyjacobs on 1/19/18.
 */
fun AppCompatActivity.fragmentLoader(fragment: Fragment, container: Int, tag: String, addToBackStack: Boolean = false) {
    val fragTransaction = this.supportFragmentManager
            .beginTransaction()
            .replace(container, fragment, tag)
    if (addToBackStack) {
        fragTransaction.addToBackStack(tag)
    }
    fragTransaction.commit()
}

fun AppCompatActivity.findFragment(tag: String): Fragment? = supportFragmentManager
        .findFragmentByTag(tag)

fun AppCompatActivity.removeFragment(tag: String) {
    val fragmentToRemove = findFragment(tag)
    fragmentToRemove?.let {
        supportFragmentManager
                .beginTransaction()
                .remove(it)
                .commit()
    }
}

fun Context.showTweetAlertDialog(message: Int, title: Int? = R.string.default_tweet_dialog_title, icon: Int? = com.twitter.sdk.android.tweetui.R.drawable.tw__ic_logo_blue, okCallback: (() -> Unit)? = null): Dialog {
    return showTweetAlertDialog(resources.getString(message), title, icon, okCallback)
}

fun Context.showTweetAlertDialog(message: String, title: Int? = R.string.default_tweet_dialog_title, icon: Int? = com.twitter.sdk.android.tweetui.R.drawable.tw__ic_logo_blue, okCallback: (() -> Unit)? = null): Dialog {
    val builder = AlertDialog.Builder(this)
            .setMessage(message)
            .setTitle(title!!)
            .setIcon(icon!!)

    okCallback?.let {
        builder.setPositiveButton(android.R.string.ok, object : DialogInterface.OnClickListener {
            override fun onClick(dialogInterface: DialogInterface?, p1: Int) {
                it.invoke()
            }
        })
    }
    val dialog = builder.create()
    dialog.show()
    return dialog

}

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_LONG) {
    val layout = LayoutInflater.from(this).inflate(R.layout.toast_message_layout, null)
    val toast = Toast(this)
    toast.view = layout
    val textView = layout.findViewById<TextView>(R.id.toast_message)
    textView.setText(message)
    toast.show()
}

