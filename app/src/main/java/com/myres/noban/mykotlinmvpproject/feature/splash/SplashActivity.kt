package com.myres.noban.mykotlinmvpproject.feature.splash


import android.os.Bundle
import com.myres.noban.mykotlinmvpproject.R
import com.myres.noban.mykotlinmvpproject.core.presenter.activity.BaseActivity
import com.myres.noban.mykotlinmvpproject.core.session.Session
import com.myres.noban.mykotlinmvpproject.di.splash.SplashModule


import javax.inject.Inject

class SplashActivity : BaseActivity<SplashPresenter>(), SplashView {
    internal var mSession: Session? = null


    @Inject
    lateinit var presenter:SplashPresenter
    override val layoutId: Int
        get() = R.layout.activity_splash


    override fun bindPresenter(): SplashPresenter {
        app.appComponent.plus(SplashModule(this)).inject(this)
        return presenter

    }

    override fun initComponents(savedInstanceState: Bundle) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun navigateToHome() {

    }

    companion object {

        val TAG = SplashActivity::class.java.simpleName
    }


}
