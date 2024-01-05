package com.kaelesty.vknewsclient.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostComment
import com.kaelesty.vknewsclient.domain.entities.PostStatType
import com.kaelesty.vknewsclient.presentation.states.NewsFeedState

class MainViewModel : ViewModel() {

	private val _newsFeedState: MutableLiveData<NewsFeedState> = MutableLiveData()
	val newsFeedState: LiveData<NewsFeedState> get() = _newsFeedState

	var savedPosts: List<Post>? = null


	init {
		val list = mutableListOf<Post>()
		repeat(50) {
			list.add(Post(it))
		}
		_newsFeedState.postValue(NewsFeedState.Posts(list))
	}

	private fun getPostComments(id: Int): List<PostComment> {
		return mutableListOf<PostComment>().apply {
			repeat(30) {
				add(
					PostComment(it)
				)
			}
		}
	}

	fun toComments(post: Post) {
		if (_newsFeedState.value !is NewsFeedState.Posts) return
		savedPosts = (_newsFeedState.value as NewsFeedState.Posts).posts
		_newsFeedState.postValue(
			NewsFeedState.Comments(
				post,
				getPostComments(post.id)
			)
		)
	}

	fun toPosts() {
		_newsFeedState.postValue(
			NewsFeedState.Posts(
				savedPosts?: mutableListOf()
			)
		)
		savedPosts = null
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
