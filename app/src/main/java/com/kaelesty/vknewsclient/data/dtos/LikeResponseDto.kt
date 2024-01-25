package com.kaelesty.vknewsclient.data.dtos

import com.google.gson.annotations.SerializedName

data class LikeResponseDto(
	@SerializedName("response") val content: LikeContentDto,
)


