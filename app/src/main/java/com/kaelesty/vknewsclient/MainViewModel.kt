package com.kaelesty.vknewsclient

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostContent
import com.kaelesty.vknewsclient.domain.entities.PostStatType
import com.kaelesty.vknewsclient.domain.entities.PostStatistics

class MainViewModel : ViewModel() {

	private val _posts: MutableLiveData<List<Post>> = MutableLiveData()
	val posts: LiveData<List<Post>> get() = _posts

	init {
		val list = mutableListOf<Post>()
		repeat(50) {
			list.add(Post(it))
		}
		_posts.postValue(list)
	}

	fun increaseStat(id: Int, type: PostStatType) {
		val oldPosts = posts.value?.toMutableList() ?: throw IllegalStateException()
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
		_posts.value = oldPosts
	}

	fun delItem(id: Int) {
		val oldPosts = posts.value?.toMutableList() ?: throw IllegalStateException()
		oldPosts.removeIf { it.id == id }
		_posts.value = oldPosts
	}
}
