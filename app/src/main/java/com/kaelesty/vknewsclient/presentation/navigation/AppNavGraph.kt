package com.kaelesty.vknewsclient.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavGraph(
	navHostController: NavHostController,
	newsFeedScreenContent: @Composable () -> Unit,
	favoritesScreenContent: @Composable () -> Unit,
	profileScreenContent: @Composable () -> Unit,
) {
	NavHost(
		navController = navHostController,
		startDestination = Screen.NewsFeed.route
	) {
		composable(Screen.NewsFeed.route) {
			newsFeedScreenContent()
		}
		composable(Screen.Favorites.route) {
			favoritesScreenContent()
		}
		composable(Screen.Profile.route) {
			profileScreenContent()
		}
	}
}