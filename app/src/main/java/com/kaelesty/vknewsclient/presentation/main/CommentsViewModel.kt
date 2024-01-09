package com.kaelesty.vknewsclient.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostComment
import com.kaelesty.vknewsclient.domain.entities.PostStatType
import com.kaelesty.vknewsclient.presentation.composables.Comment
import com.kaelesty.vknewsclient.presentation.states.CommentsState
import com.kaelesty.vknewsclient.presentation.states.NewsFeedState

class CommentsViewModel(
	val post: Post
) : ViewModel() {

	private val _commentsState: MutableLiveData<CommentsState> = MutableLiveData()
	val commentsState: LiveData<CommentsState> get() = _commentsState

	init {
		loadComments(post)
	}

	private fun loadComments(post: Post) {
		_commentsState.value = CommentsState.Comments(
			comments = mutableListOf<PostComment>().apply {
				repeat(10) {
					add(
						PostComment(0)
					)
				}
			},
			post = post
		)
	}

}
