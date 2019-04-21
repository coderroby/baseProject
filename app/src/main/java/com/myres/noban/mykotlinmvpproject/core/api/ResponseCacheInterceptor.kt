package com.myres.noban.mykotlinmvpproject.core.api

/**
 * Created by Noban Hasan on 3/12/19.
 * Audacity It Solution.
 */

import com.myres.noban.mykotlinmvpproject.core.App
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

/**
 * Interceptor to cache data and maintain it for a minute.
 *
 *
 * If the same network request is sent within a minute,
 * the response is retrieved from cache.
 */

class ResponseCacheInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        return if (java.lang.Boolean.valueOf(request.header("ApplyResponseCache"))) {
            val originalResponse = chain.proceed(request)
            originalResponse.newBuilder()
                .removeHeader("ApplyResponseCache")
                .header(
                    "Cache-Control",
                    if (App.getApp().isNetworkAvailable) "public, max-age=" + 60 else "public, only-if-cached, max-stale=" + 2419200
                )
                .build()
        } else {
            chain.proceed(request)
        }
    }
}
