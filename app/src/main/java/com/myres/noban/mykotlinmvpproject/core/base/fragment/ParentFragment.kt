package com.myres.noban.mykotlinmvpproject.core.base.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

import com.google.android.material.snackbar.Snackbar
import com.myres.noban.mykotlinmvpproject.R
import com.myres.noban.mykotlinmvpproject.core.App
import com.myres.noban.mykotlinmvpproject.core.base.activity.BaseActivityAppCompat

/**
 * Modified by Noban Hasan on 3/12/19.
 * Audacity It Solution.
 */


abstract class ParentFragment : Fragment(), BaseFragmentView {

    lateinit var app: App

    @get:LayoutRes
    protected abstract val layoutId: Int

    override val fragment: Fragment
        get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        app = App.getApplication(activity!!)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun getContext(): Context? {
        return activity
    }

    override fun getResString(@StringRes id: Int): String {
        return getString(id)
    }

    override fun getDimension(@DimenRes id: Int): Float {
        return resources.getDimension(id)
    }

    override fun showToast(text: String) {
        if (activity != null) {
            Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show()
        }
    }

    override fun showLoader() {
        if (activity != null) {
            (activity as BaseActivityAppCompat).showLoader()
        }
    }

    override fun hideLoader() {
        if (activity != null) {
            (activity as BaseActivityAppCompat).hideLoader()
        }
    }

    override fun showSnackBar(message: String, parentLayout: View, snackBarType: Int) {
        if (activity != null) {
            activity!!.runOnUiThread {
                val snackbar = Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG)
                val view = snackbar.view
                val textView = view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                textView.setTextColor(getSnackBarColor(snackBarType))
                snackbar.show()
            }
        }
    }

    private fun getSnackBarColor(snackBarType: Int): Int {
        when (snackBarType) {
            SNACK_BAR_ERROR -> return ContextCompat.getColor(activity!!, R.color.white)
            SNACK_BAR_SUCCESS -> return ContextCompat.getColor(activity!!, R.color.white)
            else -> return ContextCompat.getColor(activity!!, R.color.white)
        }
    }

    companion object {

        internal const val SNACK_BAR_ERROR = 0
        internal const val SNACK_BAR_SUCCESS = 1
        internal val SNACK_BAR_NORMAL = 2
    }
}
