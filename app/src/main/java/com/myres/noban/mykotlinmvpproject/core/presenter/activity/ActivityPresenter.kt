package com.myres.noban.mykotlinmvpproject.core.presenter.activity

import android.os.Bundle
import io.reactivex.disposables.CompositeDisposable

/**
 * Modified by Noban Hasan on 3/12/19.
 * Audacity It Solution.
 */

open class ActivityPresenter : ActivityPresenterView {

    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreatePresenter(savedInstanceState: Bundle?) {

    }

    override fun onStartPresenter() {

    }

    override fun onResumePresenter() {

    }

    override fun onPausePresenter() {

    }

    override fun onStopPresenter() {

    }

    override fun onDestroyPresenter() {
        if (compositeDisposable != null) {
            if (!compositeDisposable!!.isDisposed) {
                compositeDisposable!!.dispose()
            }
        }
    }
}
