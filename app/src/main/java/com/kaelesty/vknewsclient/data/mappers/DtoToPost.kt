package com.kaelesty.vknewsclient.data.mappers

import com.kaelesty.vknewsclient.data.dtos.NewsFeedContentDto
import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostContent
import com.kaelesty.vknewsclient.domain.entities.PostStatistics
import java.text.SimpleDateFormat
import java.util.Locale
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
					time = SimpleDateFormat("d MMMM yyyy, HH:mm", Locale.getDefault()).format(postDto.date * 1000),
					imageUrl = postDto.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url ?: "",
					text = postDto.text ?: ""
				),
				ownerId = postDto.ownerId,
				statistics = PostStatistics(
					watchers = postDto.views?.count ?: continue,
					likes = postDto.likes?.count ?: continue,
					comments = postDto.comments?.count ?: continue,
					reposts = postDto.reposts?.count ?: continue,
					isLiked = postDto.likes.isLiked == 1
				)
			)
		)
	}
	return result
}