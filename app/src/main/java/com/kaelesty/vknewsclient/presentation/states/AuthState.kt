package com.kaelesty.vknewsclient.presentation.states

sealed class AuthState {

	object Authorized: AuthState()

	object Unathorized: AuthState()

	object Initial: AuthState()
}