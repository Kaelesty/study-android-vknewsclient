package com.kaelesty.vknewsclient.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

class NavigationState(
	val navHostController: NavHostController,
) {

	fun navigateTo(route: String) {
		navHostController.navigate(route) {
			launchSingleTop = true // only one instance of screen can be on top
			popUpTo(navHostController.graph.startDestinationId) {
				saveState = true
			}
			restoreState = true
		}
	}
}

@Composable
fun rememberNavigationState(
	navHostController: NavHostController = rememberNavController()
): NavigationState {
	return remember {
		NavigationState(navHostController)
	}
}