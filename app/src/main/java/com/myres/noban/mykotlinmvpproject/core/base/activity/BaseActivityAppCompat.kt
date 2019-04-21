@file:Suppress("DEPRECATION")

package com.myres.noban.mykotlinmvpproject.core.base.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.DimenRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.myres.noban.mykotlinmvpproject.R
import com.myres.noban.mykotlinmvpproject.core.App
import com.myres.noban.mykotlinmvpproject.feature.nointernetdialog.NoInternetDialog
import com.myres.noban.mykotlinmvpproject.utility.ConnectivityReceiver
import com.orhanobut.logger.Logger
import io.reactivex.exceptions.OnErrorNotImplementedException
import io.reactivex.plugins.RxJavaPlugins
import java.util.concurrent.ExecutionException

/**
 * Modified by Noban Hasan on 3/12/19.
 * Audacity It Solution.
 */

abstract class BaseActivityAppCompat : AppCompatActivity(), ConnectivityReceiver.ConnectivityReceiverListener {

    lateinit var app: App
    private var connectivityReceiver: ConnectivityReceiver? = null
    private var noInternetDialog: NoInternetDialog? = null
    protected var showNoInternetDialog = false

    internal var progressDialog: ProgressDialog? = null

    @get:LayoutRes
    protected abstract val layoutId: Int

    val context: Context
        get() = this

    val activity: AppCompatActivity
        get() = this

    private val isLoaderShowing: Boolean
        get() = if (progressDialog == null) {
            false
        } else {
            progressDialog!!.isShowing
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        app = App.getApplication(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        connectivityReceiver = ConnectivityReceiver()

        //   Dart.inject(this);

        RxJavaPlugins.setErrorHandler { throwable ->
            if (throwable is OnErrorNotImplementedException) {
                throw ExecutionException(throwable)
            }
            Logger.e(throwable, "Error handler reported::" + throwable.message)
        }
    }

    override fun onPause() {
        super.onPause()
        if (showNoInternetDialog) {
            unregisterReceiver(connectivityReceiver)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        if (isConnected) {
            if (noInternetDialog != null) {
                if (noInternetDialog!!.dialog != null) {
                    if (noInternetDialog!!.dialog.isShowing) {
                        noInternetDialog!!.dismiss()
                    }
                }
            }
        } else {
            if (noInternetDialog == null) {
                noInternetDialog = NoInternetDialog()
                noInternetDialog!!.show(supportFragmentManager, NoInternetDialog.TAG)
            } else {
                noInternetDialog!!.show(supportFragmentManager, NoInternetDialog.TAG)
            }
        }
    }

    fun showLoader() {
        runOnUiThread {
            if (!isLoaderShowing) {
                @Suppress("DEPRECATION")
                progressDialog = ProgressDialog.show(this, null, "Please wait", true)
            }
        }
    }

    fun hideLoader() {
        runOnUiThread {
            if (isLoaderShowing) {
                progressDialog!!.dismiss()
            }
        }
    }

    fun hideKeyboard(v: View) {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(v.windowToken, 0)
    }

    protected fun startActivity(activity: Class<*>, finishSelf: Boolean) {
        val intent = Intent(this, activity)
        startActivity(intent)
        if (finishSelf) {
            finish()
        }
    }

    protected fun startActivityAndClearAll(activity: Class<*>) {
        val intent = Intent(this, activity)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
    }

    protected fun showForwardTransition(activity: Activity) {
        try {
            activity.overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left)
        } catch (e: Exception) {
            Log.e("ActivityForwardTransi", e.message)
        }

    }

    protected fun showBackwardTransition(activity: Activity) {
        try {
            activity.overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right)
        } catch (e: Exception) {
            Log.e("ActivityBackwardTransi", e.message)
        }

    }

    protected fun openUrl(url: String) {
        try {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
        } catch (e: ActivityNotFoundException) {
            e.printStackTrace()
        }

    }

    fun getDimension(@DimenRes id: Int): Float {
        return resources.getDimension(id)
    }

    fun getResString(@StringRes id: Int): String {
        return resources.getString(id)
    }

    fun showToast(text: String) {
        runOnUiThread { Toast.makeText(this, text, Toast.LENGTH_SHORT).show() }
    }

    fun showSnackBar(message: String, parentLayout: View, snackBarType: Int) {
        runOnUiThread {
            val snackbar = Snackbar.make(parentLayout, message, Snackbar.LENGTH_LONG)
            val view = snackbar.view
            val textView = view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            textView.setTextColor(getSnackBarColor(snackBarType))
            snackbar.show()
        }
    }

    private fun getSnackBarColor(snackBarType: Int): Int {
        return when (snackBarType) {
            SNACK_BAR_ERROR -> ContextCompat.getColor(this, R.color.white)
            SNACK_BAR_SUCCESS -> ContextCompat.getColor(this, R.color.white)
            else -> ContextCompat.getColor(this, R.color.white)
        }
    }

    companion object {

        internal val SNACK_BAR_ERROR = 0
        internal val SNACK_BAR_SUCCESS = 1
      //  internal val SNACK_BAR_NORMAL = 2
    }


}
