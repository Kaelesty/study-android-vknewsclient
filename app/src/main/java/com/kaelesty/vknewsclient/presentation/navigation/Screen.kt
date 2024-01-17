package com.kaelesty.vknewsclient.presentation.navigation

import android.net.Uri
import android.util.Log
import com.google.gson.Gson
import com.kaelesty.vknewsclient.domain.entities.Post

sealed class Screen(
	val route: String
) {

	companion object {
		private const val ROUTE_NEWS_FEED = "news_feed"
		private const val ROUTE_POSTS = "posts"
		private const val ROUTE_COMMENTS = "comments/{post}"

		private const val ROUTE_FAVORITES = "favorites"
		private const val ROUTE_PROFILE = "profile"
	}

	object NewsFeed: Screen(ROUTE_NEWS_FEED)
	object Posts: Screen(ROUTE_POSTS)
	object Comments: Screen(ROUTE_COMMENTS) {

		const val POST_KEY = "post"

		private const val routeWithoutArgs = "comments"

		fun getRoute(post: Post) = routeWithoutArgs +
				"/${
					Gson().toJson(post).encode()
				}"
	}

	object Favorites: Screen(ROUTE_FAVORITES)
	object Profile: Screen(ROUTE_PROFILE)
}

fun String.encode(): String {
	return Uri.encode(this) // to avoid bugs with special symbols / $ ...
}
