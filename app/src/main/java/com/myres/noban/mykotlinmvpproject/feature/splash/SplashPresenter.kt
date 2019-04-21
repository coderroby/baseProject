package com.myres.noban.mykotlinmvpproject.feature.splash

import android.os.Bundle
import android.os.Handler
import com.myres.noban.mykotlinmvpproject.core.api.Repository
import com.myres.noban.mykotlinmvpproject.core.presenter.activity.ActivityPresenter
import com.myres.noban.mykotlinmvpproject.core.session.Session

import javax.inject.Inject

/**
 * Created by Rafiqul Hasan Rony on 1/31/19.
 * Audacity It Solution.
 */

class SplashPresenter @Inject constructor(
    private val mView: SplashView,
    internal var mRepository: Repository,
    internal var mSession: Session
) : ActivityPresenter() {

    private val isLocationSaved: Boolean
        get() = !(mSession.areaId == -1 || mSession.subAreaId == -1)

    override fun onCreatePresenter(savedInstanceState: Bundle?) {
        super.onCreatePresenter(savedInstanceState)

        Handler().postDelayed({
            if (isLocationSaved) {

                mView.navigateToHome()
            } else {

            }
        }, 1000)
    }

}
