package com.kaelesty.vknewsclient.data.remote

import com.kaelesty.vknewsclient.data.dtos.LikeResponseDto
import com.kaelesty.vknewsclient.data.dtos.NewsFeedResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

	@GET("newsfeed.get")
	suspend fun getPosts(
		@Query("access_token") token: String,
		@Query("v") apiVersion: String = "5.199"
	): Response<NewsFeedResponseDto>

	@GET("likes.add")
	suspend fun like(
		@Query("access_token") token: String,
		@Query("v") apiVersion: String = "5.199",
		@Query("type") type: String = "post",
		@Query("item_id") itemId: String,
		@Query("owner_id") ownerId: Long,
	): Response<LikeResponseDto>

	@GET("likes.delete")
	suspend fun unlike(
		@Query("access_token") token: String,
		@Query("v") apiVersion: String = "5.199",
		@Query("type") type: String = "post",
		@Query("item_id") itemId: String,
		@Query("owner_id") ownerId: Long,
	): Response<LikeResponseDto>

	@GET("likes.isLiked")
	suspend fun isLiked(
		@Query("access_token") token: String,
		@Query("v") apiVersion: String = "5.199",
		@Query("type") type: String = "post",
		@Query("item_id") itemId: Long,
	)
}