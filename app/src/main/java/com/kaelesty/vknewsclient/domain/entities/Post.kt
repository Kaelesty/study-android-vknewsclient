package com.kaelesty.vknewsclient.domain.entities

import com.kaelesty.vknewsclient.R

data class Post(
	val id: String,
	val ownerId: String,
	val content: PostContent = PostContent(),
	val statistics: PostStatistics = PostStatistics(likes = 777)
)

data class PostContent(
	val groupName: String = "Kaelesty's Nest",
	val time: String = "14:25",
	val groupAvatarUrl: String = "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
	val text: String = "'Лисы роятся - к дождю' - сказал хозяин лисьей фермы, принимаясь доставать зонт из сумки.",
	val imageUrl: String = "https://media.sproutsocial.com/uploads/2017/02/10x-featured-social-media-image-size.png",
)

data class PostStatistics(
	val watchers: Int = 775,
	val reposts: Int = 54,
	val comments: Int = 101,
	val likes: Int,
	val isLiked: Boolean = false,
)

enum class PostStatType {
	WATCHERS, LIKES, COMMENTS, REPOSTS,
}