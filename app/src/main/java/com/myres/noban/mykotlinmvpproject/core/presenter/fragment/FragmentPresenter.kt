package com.myres.noban.mykotlinmvpproject.core.presenter.fragment

import io.reactivex.disposables.CompositeDisposable

/**
 * Modified by Noban Hasan on 3/12/19.
 * Audacity It Solution.
 */

abstract class FragmentPresenter : FragmentPresenterView {
    private var compositeDisposable: CompositeDisposable? = null

    override fun onCreatePresenter() {

    }

    override fun onCreateViewPresenter() {

    }

    override fun onViewCreatedPresenter() {

    }

    override fun onStartPresenter() {

    }

    override fun onResumePresenter() {

    }

    override fun onPausePresenter() {

    }

    override fun onStopPresenter() {

    }

    override fun onDestroyViewPresenter() {

    }

    override fun onDestroyPresenter() {
        if (compositeDisposable != null) {
            if (!compositeDisposable!!.isDisposed) {
                compositeDisposable!!.dispose()
            }
        }
    }

}
