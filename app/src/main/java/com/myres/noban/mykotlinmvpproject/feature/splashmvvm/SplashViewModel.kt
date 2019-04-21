package com.myres.noban.mykotlinmvpproject.feature.splashmvvm

import androidx.lifecycle.ViewModel
import com.myres.noban.mykotlinmvpproject.core.api.Repository
import com.myres.noban.mykotlinmvpproject.core.session.Session
import javax.inject.Inject

class SplashViewModel @Inject constructor(
    internal var mRepository: Repository,
    internal var mSession: Session
) :ViewModel(){

}