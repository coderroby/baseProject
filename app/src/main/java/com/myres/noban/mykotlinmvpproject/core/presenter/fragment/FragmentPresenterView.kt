package com.myres.noban.mykotlinmvpproject.core.presenter.fragment

/**
 * Modified by Noban Hasan on 3/12/19.
 * Audacity It Solution.
 */

internal interface FragmentPresenterView {
    fun onCreatePresenter()

    fun onCreateViewPresenter()

    fun onViewCreatedPresenter()

    fun onStartPresenter()

    fun onResumePresenter()

    fun onPausePresenter()

    fun onStopPresenter()

    fun onDestroyViewPresenter()

    fun onDestroyPresenter()
}
