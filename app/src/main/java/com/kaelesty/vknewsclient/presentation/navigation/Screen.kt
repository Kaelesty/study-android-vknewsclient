package com.kaelesty.vknewsclient.presentation.navigation

sealed class Screen(
	val route: String
) {

	companion object {
		private const val ROUTE_NEWS_FEED = "news_feed"
		private const val ROUTE_FAVORITES = "favorites"
		private const val ROUTE_PROFILE = "profile"
	}

	object NewsFeed: Screen(ROUTE_NEWS_FEED)
	object Favorites: Screen(ROUTE_FAVORITES)
	object Profile: Screen(ROUTE_PROFILE)
}
