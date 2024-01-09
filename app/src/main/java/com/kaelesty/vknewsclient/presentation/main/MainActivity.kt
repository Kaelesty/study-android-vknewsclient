package com.kaelesty.vknewsclient.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.kaelesty.vknewsclient.presentation.composables.HomeScreen
import com.kaelesty.vknewsclient.presentation.composables.VknewsclientTheme

class MainActivity : ComponentActivity() {

	private val viewModel by viewModels<NewsFeedViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			VknewsclientTheme {
				HomeScreen()
			}
		}
	}
}