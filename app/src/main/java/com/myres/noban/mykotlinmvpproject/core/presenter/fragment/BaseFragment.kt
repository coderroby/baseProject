package com.myres.noban.mykotlinmvpproject.core.presenter.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.myres.noban.mykotlinmvpproject.core.base.fragment.ParentFragment


/**
 * Modified by Noban Hasan on 3/12/19.
 * Audacity It Solution.
 */

internal abstract class BaseFragment<T : FragmentPresenterView> : ParentFragment() {

    private var presenter: T? = null

    protected abstract fun bindPresenter(): T

    protected abstract fun initFragmentComponents(view: View, savedInstanceState: Bundle?)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = bindPresenter()
        presenter!!.onCreatePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)
        presenter!!.onCreateViewPresenter()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter!!.onViewCreatedPresenter()
        initFragmentComponents(view, savedInstanceState)
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

    override fun onDestroyView() {
        super.onDestroyView()
        presenter!!.onDestroyViewPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter!!.onDestroyPresenter()
    }
}
