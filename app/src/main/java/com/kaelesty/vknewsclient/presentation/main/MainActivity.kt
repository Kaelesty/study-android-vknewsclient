package com.kaelesty.vknewsclient.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.kaelesty.vknewsclient.presentation.composables.NewsMainScreen
import com.kaelesty.vknewsclient.presentation.composables.VknewsclientTheme

class MainActivity : ComponentActivity() {

	private val viewModel by viewModels<MainViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			VknewsclientTheme {
				NewsMainScreen(viewModel)
			}
		}
	}
}