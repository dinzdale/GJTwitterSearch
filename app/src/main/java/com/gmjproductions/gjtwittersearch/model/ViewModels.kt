package com.gmjproductions.gjtwittersearch.model

import android.arch.lifecycle.ViewModel
import com.twitter.sdk.android.core.TwitterSession

/**
 * Created by garyjacobs on 1/18/18.
 */
data class SessionViewModel(var session : TwitterSession? = null) : ViewModel()
