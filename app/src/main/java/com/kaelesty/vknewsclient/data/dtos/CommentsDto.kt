package com.kaelesty.vknewsclient.data.dtos

import com.google.gson.annotations.SerializedName

data class CommentsDto(
	@SerializedName("count") val count: Int,
)