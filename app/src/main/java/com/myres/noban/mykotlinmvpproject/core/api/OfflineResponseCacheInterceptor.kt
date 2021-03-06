package com.myres.noban.mykotlinmvpproject.core.api

/**
 * Created by Noban Hasan on 3/12/19.
 * Audacity It Solution.
 */

import com.myres.noban.mykotlinmvpproject.core.App
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

import java.io.IOException

/**
 * Interceptor to cache data and maintain it for four weeks.
 *
 *
 * If the device is offline, stale (at most four weeks old)
 * response is fetched from the cache.
 */

class OfflineResponseCacheInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (java.lang.Boolean.valueOf(request.header("ApplyOfflineCache"))) {
            if (!App.getApp().isNetworkAvailable) {
                request = request.newBuilder()
                    .removeHeader("ApplyOfflineCache")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + 2419200)
                    .build()
            }
        }
        return chain.proceed(request)
    }
}
