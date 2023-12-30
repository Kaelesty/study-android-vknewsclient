package com.kaelesty.vknewsclient.presentation.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.kaelesty.vknewsclient.presentation.navigation.Screen

@Composable
fun Profile(
	paddingValues: PaddingValues,
) {
	Text(
		"Profile",
		modifier = Modifier
			.padding(paddingValues)
	)
}