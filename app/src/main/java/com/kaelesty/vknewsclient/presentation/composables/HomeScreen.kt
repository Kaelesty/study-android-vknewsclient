package com.kaelesty.vknewsclient.presentation.composables

import androidx.activity.compose.BackHandler
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import com.kaelesty.vknewsclient.presentation.navigation.AppNavGraph
import com.kaelesty.vknewsclient.presentation.navigation.NavigationItem
import com.kaelesty.vknewsclient.presentation.navigation.rememberNavigationState
import com.kaelesty.vknewsclient.presentation.states.NewsFeedState

@Composable
fun HomeScreen() {

	val navigationState = rememberNavigationState()

	Scaffold(
		bottomBar = {
			BottomAppBar {

				val items = listOf(
					NavigationItem.NewsFeed,
					NavigationItem.Profile,
					NavigationItem.Favorites
				)

				val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()

				items.forEach { item ->
					NavigationBarItem(
						selected = navBackStackEntry?.destination?.route == item.screen.route,
						onClick = {
							navigationState.navigateTo(item.screen.route)
						},
						icon = { Icon(item.icon, null) },
						label = { Text(
							stringResource(id = item.titleResId)
						) }
					)
				}
			}
		}
	) {
		AppNavGraph(
			navHostController = navigationState.navHostController,
			newsFeedScreenContent = {
				NewsFeed(
					paddingValues = it,
					onCommentClickListener = {}
				)
			},
			favoritesScreenContent = {
				Favorites(paddingValues = it)
			},
			profileScreenContent = {
				Profile(paddingValues = it)
			},
		)
	}
}


