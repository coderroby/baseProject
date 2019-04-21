package com.myres.noban.mykotlinmvpproject.di.splash


import com.myres.noban.mykotlinmvpproject.di.annotations.PerActivity
import com.myres.noban.mykotlinmvpproject.feature.splash.SplashActivity
import dagger.Subcomponent

/**
 * Created by Rafiqul Hasan Rony on 1/31/19.
 * Audacity It Solution.
 */
@PerActivity
@Subcomponent(modules = [SplashModule::class])
interface SplashComponent {
    fun inject(splashActivity: SplashActivity)
}
