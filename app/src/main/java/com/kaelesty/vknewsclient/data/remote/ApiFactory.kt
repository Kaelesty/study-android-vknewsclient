package com.kaelesty.vknewsclient.data.remote

import com.vk.api.sdk.okhttp.LoggingInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiFactory {

	private const val SERVER_URL = "https://api.vk.com/method/"

	private val okHttpClient = OkHttpClient.Builder()
		.addInterceptor(HttpLoggingInterceptor().apply {
			level = HttpLoggingInterceptor.Level.BODY
		})
		.build()

	val apiService: AccessApiService = Retrofit.Builder()
		.baseUrl(SERVER_URL)
		.addConverterFactory(GsonConverterFactory.create())
		.client(okHttpClient)
		.build()
		.create(AccessApiService::class.java)
}