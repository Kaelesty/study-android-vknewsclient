package com.kaelesty.vknewsclient.data.dtos

import com.google.gson.annotations.SerializedName

data class AttachmentDto(
	@SerializedName("photo") val photo: PhotoDto?,
)