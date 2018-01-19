package layout

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.telecom.Call
import android.util.AttributeSet
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.gmjproductions.gjtwittersearch.R
import com.gmjproductions.gjtwittersearch.model.SessionViewModel
import com.gmjproductions.gjtwittersearch.model.TweetsViewModel
import com.gmjproductions.gjtwittersearch.ui.SearchTweetsActivity

import com.gmjproductions.gjtwittersearch.ui.widgets.ComboBox

import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.models.Search

import kotlinx.android.synthetic.main.search_tweets.*


/**
 * Created by garyjacobs on 1/17/18.
 */
class SearchTweetsFragment : Fragment() {

    lateinit var sessionViewModel: SessionViewModel
    lateinit var tweetsViewModel: TweetsViewModel


    lateinit var myActivity: SearchTweetsActivity

    companion object {
        @JvmStatic
        val TAG = SearchTweetsFragment::class.java.simpleName
    }

    lateinit var twitterApiClient: TwitterApiClient

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.search_tweets, null)
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        myActivity = activity as SearchTweetsActivity
        sessionViewModel = ViewModelProviders.of(myActivity).get(SessionViewModel::class.java)
        tweetsViewModel = ViewModelProviders.of(myActivity).get(TweetsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get twitterApiClient from connected session
        //twitterApiClient = TwitterCore.getInstance().getApiClient(sessionViewModel.session)
        twitterApiClient = TwitterCore.getInstance().getApiClient()

        search_entry.setOnClickListener {
            val view = it as ComboBox
            val call = twitterApiClient.searchService.tweets(view.text.toString(),
                    null,
                    "en",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null)
            call.enqueue(object : Callback<Search>() {
                override fun success(result: Result<Search>?) {
                    result?.data?.tweets?.let {
                        // Update view model and get list updated
                        tweetsViewModel.tweetList.value = it
                    }

                }

                override fun failure(exception: TwitterException?) {
                    Toast.makeText(this@SearchTweetsFragment.context,"${exception!!.message}",Toast.LENGTH_LONG).show()
                }
            })
        }

    }
}
