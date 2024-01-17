package com.kaelesty.vknewsclient.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.google.gson.Gson
import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostContent

fun NavGraphBuilder.NewsFeedNavGraph(
	newsFeedScreenContent: @Composable () -> Unit,
	commentsScreenContent: @Composable (Post) -> Unit,
) {
	navigation(
		startDestination = Screen.Posts.route,
		route = Screen.NewsFeed.route
	) {
		composable(Screen.Posts.route) {
			newsFeedScreenContent()
		}
		composable(
			route = Screen.Comments.route,
			arguments = listOf(
				navArgument(Screen.Comments.POST_KEY) {
					type = NavType.StringType
				}
			)
		) {
			val post = Gson().fromJson(
				it.arguments?.getString(Screen.Comments.POST_KEY) ?: "",
				Post::class.java
			)
			commentsScreenContent(
				post
			)
		}
	}
}