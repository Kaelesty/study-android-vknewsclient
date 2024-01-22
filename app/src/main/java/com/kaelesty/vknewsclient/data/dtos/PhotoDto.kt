package com.kaelesty.vknewsclient.data.dtos

import com.google.gson.annotations.SerializedName

data class PhotoDto(
	@SerializedName("sizes") val photoUrls: List<PhotoUrlDto>,
)