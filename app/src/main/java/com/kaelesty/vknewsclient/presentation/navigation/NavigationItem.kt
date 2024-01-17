package com.kaelesty.vknewsclient.presentation.navigation

import android.media.Image
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.kaelesty.vknewsclient.R

sealed class NavigationItem(
	val screen: Screen,
	val titleResId: Int,
	val icon: ImageVector
) {

	object Posts: NavigationItem(
		Screen.NewsFeed,
		R.string.navitem_title_newsfeed,
		Icons.Outlined.Home,
	)

	object Favorites: NavigationItem(
		Screen.Favorites,
		R.string.navitem_title_favorites,
		Icons.Outlined.FavoriteBorder,
	)

	object Profile: NavigationItem(
		Screen.Profile,
		R.string.navitem_title_profile,
		Icons.Outlined.AccountCircle,
	)
}