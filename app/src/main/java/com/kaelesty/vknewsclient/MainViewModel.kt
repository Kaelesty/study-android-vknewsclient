package com.kaelesty.vknewsclient

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostStatType

class MainViewModel : ViewModel() {

	private val _post = MutableLiveData(Post())
	val post: LiveData<Post> get() = _post

	fun increaseStat(type: PostStatType) {
		val oldPost = _post.value ?: throw IllegalStateException()
		_post.value = oldPost.copy(
			statistics = oldPost.statistics.copyWithIncrease(type)
		)
		Log.d("MainViewModel", "VM ${post.value?.statistics?.likes.toString()}")
	}
}
