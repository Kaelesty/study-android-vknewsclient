package com.kaelesty.vknewsclient.data.dtos

import com.google.gson.annotations.SerializedName

data class PhotoUrlDto(
	@SerializedName("url") val url: String,
)