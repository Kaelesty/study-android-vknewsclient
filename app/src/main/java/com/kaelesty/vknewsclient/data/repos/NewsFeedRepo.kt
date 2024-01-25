package com.kaelesty.vknewsclient.data.repos

import android.util.Log
import com.kaelesty.vknewsclient.data.mappers.toPosts
import com.kaelesty.vknewsclient.data.remote.ApiFactory
import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.repos.INewsFeedRepo

class NewsFeedRepo(
	private val token: String?
): INewsFeedRepo {

	val apiService = ApiFactory.apiService

	override suspend fun getFeedPosts(): List<Post>? {

		token?.let {
			Log.d("NewsFeedRepo.kt", it)
			val response = apiService.getPosts(it)

			return when (response.code()) {
				200 -> {
					val responseDto = response.body()
					responseDto?.content?.toPosts()
				}

				else -> {
					null
				}
			}
		}

		return null
	}

	override suspend fun likePost(post: Post): Int? {

		token?.let {
			val response = apiService.like(
				token = it,
				itemId = post.id,
				ownerId = post.ownerId.toLong(),
			)

			return when (response.code()) {
				200 -> {
					response.body()?.content?.likes
				}
				else -> {
					null
				}
			}
		}

		return null
	}

	override suspend fun unlikePost(post: Post): Int? {

		token?.let {
			val response = apiService.unlike(
				token = it,
				itemId = post.id,
				ownerId = post.ownerId.toLong(),
			)

			return when (response.code()) {
				200 -> {
					response.body()?.content?.likes
				}
				else -> {
					null
				}
			}
		}

		return null
	}
}