package com.kaelesty.vknewsclient.data.dtos

import com.google.gson.annotations.SerializedName

data class NewsFeedResponseDto(
	@SerializedName("response") val content: NewsFeedContentDto,
)


