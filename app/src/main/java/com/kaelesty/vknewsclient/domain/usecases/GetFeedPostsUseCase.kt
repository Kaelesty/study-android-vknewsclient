package com.kaelesty.vknewsclient.domain.usecases

import com.kaelesty.vknewsclient.domain.repos.INewsFeedRepo

class GetFeedPostsUseCase(
	private val repo: INewsFeedRepo
) {

	suspend operator fun invoke() = repo.getFeedPosts()
}