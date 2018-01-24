package layout

import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.gmjproductions.gjtwittersearch.R
import com.gmjproductions.gjtwittersearch.model.TweetsViewModel
import com.gmjproductions.gjtwittersearch.ui.SearchTweetsActivity
import com.gmjproductions.gjtwittersearch.ui.showTweetAlertDialog
import com.gmjproductions.gjtwittersearch.ui.widgets.ComboBox
import com.twitter.sdk.android.core.*
import com.twitter.sdk.android.core.models.Search

import kotlinx.android.synthetic.main.search_tweets.*


/**
 * Created by garyjacobs on 1/17/18.
 */
class SearchTweetsFragment : Fragment() {

    lateinit var tweetsViewModel: TweetsViewModel
    lateinit var isomapping: Array<String>
    var selectedLanguage: String? = null
    var selectedCount: Int = 0
    var loadingDialog: Dialog? = null
    // shared preference keys
    var LANGUAGE = "LANGUAGE"
    var COUNT = "COUNT"

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
        tweetsViewModel = ViewModelProviders.of(myActivity).get(TweetsViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search_entry.setOnClickListener {

            val combobox = it as ComboBox

            loadingDialog = context?.showTweetAlertDialog(R.string.loading_tweets)

            tweetsViewModel.searchTweets(combobox.text.toString(), selectedLanguage!!, selectedCount, { pair ->

                loadingDialog!!.dismiss()
                when (pair.first) {
                    0 -> context?.showTweetAlertDialog(R.string.no_search_results, okCallback = {})
                    -1 -> context?.showTweetAlertDialog(pair.second!!, icon = android.R.drawable.ic_dialog_alert, okCallback = {})
                }
            })
        }
        isomapping = resources.getStringArray(R.array.language_iso_639_1)
        setUpLanguageSpinner(language_spinner, { selectedLanguage = it })
        setUpCountSpinner(count_spinner, { selectedCount = it })
    }


    fun setUpLanguageSpinner(spinner: Spinner, isoMappingCallback: ((String) -> Unit)?) {
        val adapter = ArrayAdapter.createFromResource(this.context, R.array.language_choices, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, index: Int, id: Long) {
                isoMappingCallback?.let {
                    isoMappingCallback.invoke(isomapping[index])
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    fun setUpCountSpinner(spinner: Spinner, countCallback: ((Int) -> Unit)?) {
        val adapter = ArrayAdapter.createFromResource(this.context, R.array.tweet_counts, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(adapterView: AdapterView<*>?) {
            }

            override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, index: Int, id: Long) {
                countCallback?.let {
                    val value = adapter!!.getItem(index).toString().toInt()
                    countCallback.invoke(value)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // restore preferences
        var sharedPreferences = context!!.getSharedPreferences(TAG, 0)
        language_spinner.setSelection(sharedPreferences.getInt(LANGUAGE, 0))
        count_spinner.setSelection(sharedPreferences.getInt(COUNT, 0))

        selectedCount = count_spinner.selectedItem.toString().toInt()
    }

    override fun onStop() {
        super.onStop()
        // save preferences
        val sharedPreferences = context!!.getSharedPreferences(TAG, 0)
        sharedPreferences.edit()
                .putInt(LANGUAGE, language_spinner.selectedItemPosition)
                .putInt(COUNT, count_spinner.selectedItemPosition)
                .commit()
    }
}
