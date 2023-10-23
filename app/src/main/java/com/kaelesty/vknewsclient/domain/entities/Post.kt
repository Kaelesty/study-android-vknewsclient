package com.kaelesty.vknewsclient.domain.entities

import com.kaelesty.vknewsclient.R

data class Post(
	val id: Int,
	val content: PostContent = PostContent(),
	val statistics: PostStatistics = PostStatistics()
)

data class PostContent(
	val groupName: String = "Kaelesty's Nest",
	val time: String = "14:25",
	val groupAvatarId: Int = R.drawable.fox,
	val text: String = "'Лисы роятся - к дождю' - сказал хозяин лисьей фермы, принимаясь доставать зонт из сумки.",
	val imageId: Int = R.drawable.foxes,
)

data class PostStatistics(
	val watchers: Int = 775,
	val reposts: Int = 54,
	val comments: Int = 101,
	val likes: Int = 720,
)

enum class PostStatType {
	WATCHERS, LIKES, COMMENTS, REPOSTS,
}