package com.kaelesty.vknewsclient.presentation.tools

fun formatStat(value: Int): String {

	if (value < 10000) {
		return value.toString()
	}

	return "${String.format("%.3f", value.toFloat() / 1000)}K"
}