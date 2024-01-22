package com.kaelesty.vknewsclient.data.remote

import com.kaelesty.vknewsclient.data.dtos.NewsFeedResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AccessApiService {
	@GET("newsfeed.get?v=5.199")
	suspend fun getPosts(
		@Query("access_token") token: String,
	): Response<NewsFeedResponseDto>
}