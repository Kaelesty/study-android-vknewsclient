package com.kaelesty.vknewsclient.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostComment
import com.kaelesty.vknewsclient.domain.entities.PostStatType
import com.kaelesty.vknewsclient.presentation.states.NewsFeedState

class NewsFeedViewModel : ViewModel() {

	private val _newsFeedState: MutableLiveData<NewsFeedState> = MutableLiveData()
	val newsFeedState: LiveData<NewsFeedState> get() = _newsFeedState


	init {
		val list = mutableListOf<Post>()
		repeat(50) {
			list.add(Post(it))
		}
		_newsFeedState.postValue(NewsFeedState.Posts(list))
	}

	fun increaseStat(id: Int, type: PostStatType) {
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

	fun delItem(id: Int) {
		val oldPosts = (_newsFeedState.value as NewsFeedState.Posts).posts.toMutableList()
		oldPosts.removeIf { it.id == id }
		_newsFeedState.postValue(NewsFeedState.Posts(oldPosts))
	}
}
