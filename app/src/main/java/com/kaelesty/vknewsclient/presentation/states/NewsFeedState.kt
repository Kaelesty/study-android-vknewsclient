package com.kaelesty.vknewsclient.presentation.states

import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostComment

sealed class NewsFeedState {

	object Initial: NewsFeedState()

	data class Posts(val posts: List<Post>): NewsFeedState()

	object Loading : NewsFeedState()
}