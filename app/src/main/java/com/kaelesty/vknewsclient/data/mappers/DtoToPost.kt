package com.kaelesty.vknewsclient.data.mappers

import com.kaelesty.vknewsclient.data.dtos.NewsFeedContentDto
import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostContent
import com.kaelesty.vknewsclient.domain.entities.PostStatistics
import kotlin.math.absoluteValue


fun NewsFeedContentDto.toPosts(): List<Post> {

	val result = mutableListOf<Post>()

	for (i in 0 until this.posts.size) {
		val postDto = this.posts[i]
		val groupDto = this.groups.find { it.id == postDto.sourceId.absoluteValue } ?: continue
		result.add(
			Post(
				id = postDto.id,
				content = PostContent(
					groupName = groupDto.name,
					groupAvatarUrl = groupDto.photoUrl,
					time = postDto.date.toString(),
					imageUrl = postDto.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url ?: "",
					text = postDto.text
				),
				statistics = PostStatistics(
					watchers = postDto.views.count,
					likes = postDto.likes.count,
					comments = postDto.comments.count,
					reposts = postDto.reposts.count
				)
			)
		)
	}
	return result
}