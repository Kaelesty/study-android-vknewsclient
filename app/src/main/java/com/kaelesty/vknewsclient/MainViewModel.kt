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

	private val _post = MutableLiveData(Post()
		.copy(
			PostContent(),
			PostStatistics().copy(likes = 740)
		)
	)
	val post: LiveData<Post> get() = _post

	fun increaseStat(type: PostStatType) {
		val oldValue = post.value ?: throw IllegalStateException()
		val oldStatistics = oldValue.statistics
		val newStatistics = when(type) {
			PostStatType.REPOSTS -> oldStatistics.copy(reposts = oldStatistics.reposts + 1)
			PostStatType.LIKES -> oldStatistics.copy(likes = oldStatistics.likes + 1)
			PostStatType.COMMENTS -> oldStatistics.copy(comments = oldStatistics.comments + 1)
			else -> oldStatistics
		}
		_post.value = (post.value ?: throw IllegalStateException()).copy(
			content = oldValue.content,
			statistics = newStatistics
		)
		Log.d("MainViewModel", "VM ${post.value?.statistics?.likes}")
	}
}
