package layout

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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
<<<<<<< HEAD
import com.gmjproductions.gjtwittersearch.ui.widgets.ComboBox
=======
>>>>>>> 87d7db6f0b3445a642258af322f7b8a363555189
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.models.Search

import kotlinx.android.synthetic.main.search_tweets.*


/**
 * Created by garyjacobs on 1/17/18.
 */
class SearchTweetsFragment : Fragment() {

    lateinit var sessionViewModel: SessionViewModel
<<<<<<< HEAD
    lateinit var tweetsViewModel: TweetsViewModel
=======
    lateinit var tweetsViewModel : TweetsViewModel
>>>>>>> 87d7db6f0b3445a642258af322f7b8a363555189

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

<<<<<<< HEAD
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
=======
        search_entry.setOnEditorActionListener { textView, actionId, _ ->
            var retValue = false
            if (actionId == KeyEvent.KEYCODE_CALL || actionId == KeyEvent.KEYCODE_ENDCALL) {
                retValue = true
//                        dismissKeyboard()
//                        dismissDropDown()
//                        callOnClick()

                val call = twitterApiClient.searchService.tweets(textView.text.toString(),
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
                            tweetsViewModel.tweetList.value = it
                        }
>>>>>>> 87d7db6f0b3445a642258af322f7b8a363555189
                    }
                }

<<<<<<< HEAD
                override fun failure(exception: TwitterException?) {
                    Toast.makeText(context, "Search Failed: ${exception!!.message}", Toast.LENGTH_LONG).show()
                }
            })

=======
                    override fun failure(exception: TwitterException?) {
                        Toast.makeText(context, "Search Failed: ${exception!!.message}", Toast.LENGTH_LONG).show()
                    }
                })

            }
            retValue
>>>>>>> 87d7db6f0b3445a642258af322f7b8a363555189
        }
    }
}
