package com.kaelesty.vknewsclient.presentation.states

import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostComment

sealed class NewsFeedState {

	data class Posts(val posts: List<Post>): NewsFeedState()

	data class Comments(
		val post: Post,
		val comments: List<PostComment>
	): NewsFeedState()
}