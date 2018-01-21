package com.gmjproductions.gjtwittersearch.ui.widgets

import android.content.Context
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.widget.AppCompatAutoCompleteTextView
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import io.reactivex.Observable


class ComboBox : AppCompatAutoCompleteTextView {

    private var listener: View.OnClickListener? = null

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    init {

        onItemClickListener = object : AdapterView.OnItemClickListener {
            override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                performCompletion()
            }
        }

        onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                performCompletion()
            }
        }
        /**
         * Listen for single tap motionevents and clear text if tapped on eraser image
         */
        getSingleTapObservable(this )
                .subscribe { motionEvent ->
                    val DRAWABLE_RIGHT = 2;
                    if (motionEvent.getRawX() >= (getRight() - getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        text.clear()
                    }
                }
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        this.listener = listener
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        setOnEditorActionListener { _, actionId, _ ->
            var retValue = false
            if (actionId == KeyEvent.KEYCODE_CALL || actionId == KeyEvent.KEYCODE_ENDCALL) {
                (adapter as ArrayAdapter<String>).clear()
                retValue = true
                dismissKeyboard()
                dismissDropDown()
                callOnClick()
            }
            retValue
        }


        setAdapter(ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line, arrayOf()))


    }

    override fun callOnClick(): Boolean {
        var status = false
        listener?.let {
            status = true
            it.onClick(this)
        }
        return status
    }

    fun updateComboBoxSelections(newList: List<String>) {
        val theAdapter = adapter as ArrayAdapter<String>
        theAdapter.clear()
        theAdapter.addAll(newList)
        theAdapter.notifyDataSetChanged()
        performFiltering("", 0)
        showDropDown()
        showKeyboard()
    }


    private fun dismissKeyboard(hide_flag: Int = InputMethodManager.RESULT_UNCHANGED_SHOWN) {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, hide_flag)
    }

    private fun showKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT);
    }

    fun getCurrentText(): String? {
        return text.toString()
    }

    /**
     * Get an observable for listening to single click MotionEvents
     */
    fun getSingleTapObservable(view: View): Observable<MotionEvent> {
        return Observable.create<MotionEvent> { emitter ->
            val gestureDetecor = GestureDetectorCompat(view.context, object : GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapConfirmed(motionEvent: MotionEvent): Boolean {
                    emitter.onNext(motionEvent!!)
                    return super.onSingleTapConfirmed(motionEvent)
                }
            })

            view.setOnTouchListener { v, event ->
                gestureDetecor.onTouchEvent(event)
            }
        }
    }
}
