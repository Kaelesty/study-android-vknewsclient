package com.kaelesty.vknewsclient.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kaelesty.vknewsclient.presentation.composables.HomeScreen
import com.kaelesty.vknewsclient.presentation.composables.LoginScreen
import com.kaelesty.vknewsclient.presentation.composables.VknewsclientTheme
import com.kaelesty.vknewsclient.presentation.states.AuthState
import com.vk.api.sdk.VK
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope

class MainActivity : ComponentActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setContent {
			VknewsclientTheme {

				val viewModel: MainViewModel = viewModel()
				val authState = viewModel.authState.observeAsState(AuthState.Initial)

				val launcher = rememberLauncherForActivityResult(
					contract = VK.getVKAuthActivityResultContract(),
					onResult = {
						viewModel.handleAuthResult(it)
					}
				)


				when (authState.value) {
					is AuthState.Initial -> {

					}

					is AuthState.Authorized -> {
						HomeScreen()
					}
					is AuthState.Unathorized -> {
						LoginScreen(
							loginCallback = {
								launcher.launch(arrayListOf(VKScope.WALL, VKScope.FRIENDS))
							}
						)
					}
				}
			}
		}
	}
}