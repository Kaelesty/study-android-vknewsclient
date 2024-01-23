package com.kaelesty.vknewsclient.presentation.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kaelesty.vknewsclient.data.repos.NewsFeedRepo
import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostComment
import com.kaelesty.vknewsclient.domain.entities.PostStatType
import com.kaelesty.vknewsclient.domain.usecases.GetFeedPostsUseCase
import com.kaelesty.vknewsclient.presentation.states.AuthState
import com.kaelesty.vknewsclient.presentation.states.NewsFeedState
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsFeedViewModel(val app: Application) : AndroidViewModel(app) {

	private val _newsFeedState: MutableLiveData<NewsFeedState> = MutableLiveData()
	val newsFeedState: LiveData<NewsFeedState> get() = _newsFeedState

	private val repo = NewsFeedRepo(
		VKAccessToken.restore(VKPreferencesKeyValueStorage(app))?.accessToken.also {
			Log.d("NewsFeedVM.kt", it ?: "-1")
		}
	)
	private val getFeedPostsUseCase = GetFeedPostsUseCase(repo)


	init {
		loadPosts()
	}

	private fun loadPosts() {
		viewModelScope.launch(Dispatchers.IO) {
			while (true) {
				val posts = getFeedPostsUseCase()
				Log.d("NewsFeedVM.kt", (posts ?: listOf()).size.toString())
				if (posts != null) {
					_newsFeedState.postValue(NewsFeedState.Posts(posts))
					break
				}
				_newsFeedState.postValue(NewsFeedState.Loading)
			}
		}
	}

	fun increaseStat(id: String, type: PostStatType) {
		val oldPosts = (_newsFeedState.value as NewsFeedState.Posts).posts.toMutableList()
		oldPosts.replaceAll {
			if (it.id != id) {
				it
			}
			else {
				val oldStatistics = it.statistics
				it.copy(statistics = when(type) {
					PostStatType.REPOSTS -> oldStatistics.copy(reposts = oldStatistics.reposts + 1)
					PostStatType.LIKES -> oldStatistics.copy(likes = oldStatistics.likes + 1)
					PostStatType.COMMENTS -> oldStatistics.copy(comments = oldStatistics.comments + 1)
					else -> oldStatistics
				})
			}
		}
		_newsFeedState.postValue(NewsFeedState.Posts(oldPosts))
	}

	fun delItem(id: String) {
		val oldPosts = (_newsFeedState.value as NewsFeedState.Posts).posts.toMutableList()
		oldPosts.removeIf { it.id == id }
		_newsFeedState.postValue(NewsFeedState.Posts(oldPosts))
	}
}
