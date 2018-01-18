package layout

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.AppCompatEditText
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.gmjproductions.gjtwittersearch.R
import com.gmjproductions.gjtwittersearch.model.SessionViewModel
import com.gmjproductions.gjtwittersearch.ui.MainActivity
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.models.Search
import com.twitter.sdk.android.core.services.params.Geocode
import kotlinx.android.synthetic.main.search_tweets.*
import retrofit2.http.Query

/**
 * Created by garyjacobs on 1/17/18.
 */
class SearchTweetsFragment : Fragment() {

    lateinit var sessionViewModel: SessionViewModel
    lateinit var myActivity: MainActivity

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
        myActivity = activity as MainActivity
        sessionViewModel = ViewModelProviders.of(myActivity).get(SessionViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // get twitterApiClient from connected session
        twitterApiClient = TwitterCore.getInstance().getApiClient(sessionViewModel.session)
        savedInstanceState?.let {

        }

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
                        val searchresult = result?.data
                        Toast.makeText(context, "Got result", Toast.LENGTH_LONG).show()
                    }

                    override fun failure(exception: TwitterException?) {
                        Toast.makeText(context, "Search Failed: ${exception!!.message}", Toast.LENGTH_LONG).show()
                    }
                })

            }
            retValue
        }

    }
}