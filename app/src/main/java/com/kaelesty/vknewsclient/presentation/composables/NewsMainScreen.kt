package com.kaelesty.vknewsclient.presentation.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kaelesty.vknewsclient.presentation.main.MainViewModel
import com.kaelesty.vknewsclient.presentation.navigation.AppNavGraph
import com.kaelesty.vknewsclient.presentation.navigation.NavigationItem

@Composable
fun NewsMainScreen(
	viewModel: MainViewModel,
) {
	val posts by viewModel.posts.observeAsState(initial = mutableListOf())

	val selectedNavButtonIndex = remember {
		mutableIntStateOf(0)
	}

	val navHostController = rememberNavController()

	Scaffold(
		bottomBar = {
			BottomAppBar {

				val items = listOf(
					NavigationItem.NewsFeed,
					NavigationItem.Profile,
					NavigationItem.Favorites
				)

				val navBackStackEntry by navHostController.currentBackStackEntryAsState()

				items.forEach { item ->
					NavigationBarItem(
						selected = navBackStackEntry?.destination?.route == item.screen.route,
						onClick = {
								  navHostController.navigate(item.screen.route)
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
			navHostController = navHostController,
			newsFeedScreenContent = {
				NewsFeed(
					paddingValues = it,
					posts = posts,
					viewModel = viewModel
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


