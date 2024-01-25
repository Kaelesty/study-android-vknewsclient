package com.kaelesty.vknewsclient.domain.usecases

import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.repos.INewsFeedRepo

class LikePostUseCase(
	private val repo: INewsFeedRepo
) {

	suspend operator fun invoke(post: Post) = repo.likePost(post)
}