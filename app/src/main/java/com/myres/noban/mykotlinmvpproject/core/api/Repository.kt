package com.myres.noban.mykotlinmvpproject.core.api

import android.content.Context

/**
 * Created by Noban Hasan on 3/12/19.
 * Audacity It Solution.
 */
class Repository(context: Context) {
    private val apiService: APIService = RetrofitAPIFactory.createService(context, 30)


}
