package com.myres.noban.mykotlinmvpproject.core.presenter.activity

import android.os.Bundle
import android.util.Log
import com.myres.noban.mykotlinmvpproject.core.base.activity.BaseActivityAppCompat


/**
 * Modified by Noban Hasan on 3/12/19.
 * Audacity It Solution.
 */

abstract class BaseActivity<T : ActivityPresenterView> : BaseActivityAppCompat() {

    private var presenter: T? = null

    protected abstract fun bindPresenter(): T

    protected  abstract fun initComponents(savedInstanceState: Bundle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = bindPresenter()
        if (savedInstanceState != null) {
            presenter!!.onCreatePresenter(savedInstanceState)
            initComponents(savedInstanceState)

        } else {
            Log.e("Error", "Null")
        }

    }

    override fun onStart() {
        super.onStart()
        presenter!!.onStartPresenter()
    }

    override fun onResume() {
        super.onResume()
        presenter!!.onResumePresenter()
    }

    override fun onPause() {
        super.onPause()
        presenter!!.onPausePresenter()
    }

    override fun onStop() {
        super.onStop()
        presenter!!.onStopPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.onDestroyPresenter()
    }
}
