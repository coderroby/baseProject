package com.myres.noban.mykotlinmvpproject.di

import com.myres.noban.mykotlinmvpproject.di.splash.SplashComponent
import com.myres.noban.mykotlinmvpproject.di.splash.SplashModule
import dagger.Component

import javax.inject.Singleton

/**
 * Created by Rafiqul Hasan Rony on 1/31/19.
 * Audacity It Solution.
 */

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
     fun plus(splashModule: SplashModule): SplashComponent

}
