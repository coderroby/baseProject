package com.myres.noban.mykotlinmvpproject.core.api


import io.reactivex.Flowable
import retrofit2.http.GET

/**
 * Modified by Noban Hasan on 3/12/19.
 * Audacity It Solution.
 */
interface APIService {


    @get:GET("get-all-outlets")
    val allOutlets: Flowable<Any>


}
