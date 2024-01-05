package com.kaelesty.vknewsclient.domain.entities

import com.kaelesty.vknewsclient.R

data class PostComment(
	val id: Int,
	val author: String = "Author",
	val text: String = "Long comment text",
	val avatarImageID: Int = R.drawable.user_svgrepo_com,
	val publicationDate: String = "12:40",
)