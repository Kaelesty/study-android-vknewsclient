package com.kaelesty.vknewsclient.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.kaelesty.vknewsclient.domain.entities.Post

@Composable
fun AppNavGraph(
	navHostController: NavHostController,
	newsFeedScreenContent: @Composable () -> Unit,
	commentsScreenContent: @Composable (Post) -> Unit,
	favoritesScreenContent: @Composable () -> Unit,
	profileScreenContent: @Composable () -> Unit,
) {
	NavHost(
		navController = navHostController,
		startDestination = Screen.NewsFeed.route
	) {
		NewsFeedNavGraph(
			newsFeedScreenContent, commentsScreenContent
		)

		composable(Screen.Favorites.route) {
			favoritesScreenContent()
		}

		composable(Screen.Profile.route) {
			profileScreenContent()
		}
	}
}