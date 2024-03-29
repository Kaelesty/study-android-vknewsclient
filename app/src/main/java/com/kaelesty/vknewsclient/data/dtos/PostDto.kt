package com.kaelesty.vknewsclient.data.dtos

import com.google.gson.annotations.SerializedName

data class PostDto(
	@SerializedName("id") val id: String,
	@SerializedName("owner_id") val ownerId: String,
	@SerializedName("source_id") val sourceId: Long,
	@SerializedName("is_favorite") val isFavorite: Boolean,
	@SerializedName("text") val text: String?,
	@SerializedName("date") val date: Long,
	@SerializedName("views") val views: ViewsDto?,
	@SerializedName("reposts") val reposts: RepostsDto?,
	@SerializedName("likes") val likes: LikesDto?,
	@SerializedName("comments") val comments: CommentsDto?,
	@SerializedName("attachments") val attachments: List<AttachmentDto>?
)


