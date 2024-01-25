package com.kaelesty.vknewsclient.data.dtos

import com.google.gson.annotations.SerializedName

data class LikesDto(
	@SerializedName("count") val count: Int,
	@SerializedName("user_likes") val isLiked: Int,
)