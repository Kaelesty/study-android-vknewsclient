package com.kaelesty.vknewsclient.presentation.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kaelesty.vknewsclient.presentation.navigation.Screen

@Composable
fun Favorites(
	paddingValues: PaddingValues,
) {
	Text(
		"Favorites",
		modifier = Modifier
			.padding(paddingValues)
	)
}