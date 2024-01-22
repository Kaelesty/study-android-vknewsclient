package com.kaelesty.vknewsclient.presentation.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kaelesty.vknewsclient.presentation.states.AuthState
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKPreferencesKeyValueStorage
import com.vk.api.sdk.auth.VKAccessToken
import com.vk.api.sdk.auth.VKAuthenticationResult

class MainViewModel(application: Application): AndroidViewModel(application) {

	private val _authState = MutableLiveData<AuthState>(AuthState.Initial)
	val authState: LiveData<AuthState> get() = _authState

	init {
		val storage = VKPreferencesKeyValueStorage(application)
		val token = VKAccessToken.restore(storage)
		Log.d("MainViewModel.kt", token?.accessToken?: "-1")
		_authState.value = if (VK.isLoggedIn()) AuthState.Authorized else AuthState.Unathorized
	}

	fun handleAuthResult(result: VKAuthenticationResult) {
		when (result) {
			is VKAuthenticationResult.Success -> {
				_authState.value = AuthState.Authorized
			}
			is VKAuthenticationResult.Failed -> {
				_authState.value = AuthState.Unathorized
			}
		}
	}
}