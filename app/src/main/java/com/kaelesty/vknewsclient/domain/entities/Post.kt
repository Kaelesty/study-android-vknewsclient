package com.kaelesty.vknewsclient.domain.entities

import com.kaelesty.vknewsclient.R

data class Post(
	val content: PostContent = PostContent(),
	val statistics: PostStatistics = PostStatistics()
)

data class PostContent(
	var groupName: String = "Kaelesty's Nest",
	var time: String = "14:25",
	var groupAvatarId: Int = R.drawable.fox,
	var text: String = "'Лисы роятся - к дождю' - сказал хозяин лисьей фермы, принимаясь доставать зонт из сумки.",
	var imageId: Int = R.drawable.foxes,
)

data class PostStatistics(
	var watchers: Int = 775,
	var reposts: Int = 54,
	var comments: Int = 101,
	var likes: Int = 720,
) {
	fun copyWithIncrease(type: PostStatType): PostStatistics {
		return this.apply {
			when (type) {
				PostStatType.WATCHERS -> {}
				PostStatType.REPOSTS -> reposts += 1
				PostStatType.COMMENTS -> comments += 1
				PostStatType.LIKES -> likes += 1
			}
		}
	}
}

enum class PostStatType {
	WATCHERS, LIKES, COMMENTS, REPOSTS,
}