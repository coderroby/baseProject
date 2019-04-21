package com.myres.noban.mykotlinmvpproject.di

import android.app.Application

import com.myres.noban.mykotlinmvpproject.core.App
import com.myres.noban.mykotlinmvpproject.core.api.Repository
import com.myres.noban.mykotlinmvpproject.core.session.Session
import com.myres.noban.mykotlinmvpproject.core.session.SharedPreferenceSessionImpl
import dagger.Module
import dagger.Provides

import javax.inject.Singleton

/**
 * Created by Rafiqul Hasan Rony on 1/31/19.
 * Audacity It Solution.
 */
@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApp(): App {
        return application as App
    }

    @Provides
    @Singleton
    fun provideRepository(): Repository {
        return Repository(application)
    }

    @Provides
    @Singleton
     fun provideSession(): Session {
        return SharedPreferenceSessionImpl()
    }
}
