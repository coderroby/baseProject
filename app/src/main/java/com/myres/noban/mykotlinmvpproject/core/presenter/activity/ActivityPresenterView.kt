package com.myres.noban.mykotlinmvpproject.core.presenter.activity

import android.os.Bundle

/**
 * Modified by Noban Hasan on 3/12/19.
 * Audacity It Solution.
 */

interface ActivityPresenterView {
    fun onCreatePresenter(savedInstanceState: Bundle?)

    fun onStartPresenter()

    fun onResumePresenter()

    fun onPausePresenter()

    fun onStopPresenter()

    fun onDestroyPresenter()
}
