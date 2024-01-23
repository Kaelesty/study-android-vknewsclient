package com.kaelesty.vknewsclient.domain.repos

import com.kaelesty.vknewsclient.domain.entities.Post

interface INewsFeedRepo {

	suspend fun getFeedPosts(): List<Post>?
}