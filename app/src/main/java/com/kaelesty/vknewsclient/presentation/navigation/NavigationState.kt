package com.kaelesty.vknewsclient.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.kaelesty.vknewsclient.domain.entities.Post

class NavigationState(
	val navHostController: NavHostController,
) {

	fun navigateTo(route: String) {
		navHostController.navigate(route) {
			launchSingleTop = true // only one instance of screen can be on top
			popUpTo(navHostController.graph.findStartDestination().id) {
				saveState = true
			}
			restoreState = true
		}
	}

	fun navigateToComments(post: Post) {
		navHostController.navigate(Screen.Comments.getRoute(post))
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