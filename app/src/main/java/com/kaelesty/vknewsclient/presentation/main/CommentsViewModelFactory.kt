package com.kaelesty.vknewsclient.presentation.main

import android.widget.ViewSwitcher.ViewFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kaelesty.vknewsclient.domain.entities.Post

class CommentsViewModelFactory(
	private val post: Post
): ViewModelProvider.Factory {
	override fun <T : ViewModel> create(modelClass: Class<T>): T {
		return CommentsViewModel(post) as T
	}
}
