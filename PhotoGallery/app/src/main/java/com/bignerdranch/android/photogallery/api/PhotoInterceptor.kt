package com.bignerdranch.android.photogallery.api

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

private const val API_KEY= "b246d49ceed86708fafe77c28371cf06"
class PhotoInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // create an HTTP request url
        val originalRequest: Request = chain.request()
        val newUrl: HttpUrl = originalRequest.url.newBuilder()
                // add pre-defined values to request parameters
            .addQueryParameter("api_key", API_KEY)
            .addQueryParameter("format", "json")
            .addQueryParameter("nojsoncallback", "1")
            .addQueryParameter("extras", "url_s")
            .addQueryParameter("safesearch", "1")
            .build()
        // create a new HTTP request url based on the new parameter values
        val newRequest: Request = originalRequest.newBuilder()
            .url(newUrl)
            .build()
        // issue the new request
        return chain.proceed(newRequest)
    }
}