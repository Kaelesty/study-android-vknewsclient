package com.kaelesty.vknewsclient.presentation.states

import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostComment

sealed class CommentsState {

	object Initial: CommentsState()

	data class Comments(
		val comments: List<PostComment>,
		val post: Post
	): CommentsState()
}