package com.myres.noban.mykotlinmvpproject.di.splash


import com.myres.noban.mykotlinmvpproject.di.annotations.PerActivity
import com.myres.noban.mykotlinmvpproject.feature.splash.SplashActivity
import com.myres.noban.mykotlinmvpproject.feature.splash.SplashView
import dagger.Module
import dagger.Provides

/**
 * Created by Rafiqul Hasan Rony on 1/31/19.
 * Audacity It Solution.
 */

@Module
class SplashModule(private val splashView: SplashView) {


    @PerActivity
    @Provides
    fun provideView(): SplashView {
        return splashView
    }
}
