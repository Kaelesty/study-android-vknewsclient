package com.kaelesty.vknewsclient.ui.theme

import android.app.Activity
import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleOwner
import com.kaelesty.vknewsclient.MainViewModel
import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostStatType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsMainScreen(
	viewModel: MainViewModel,
	lifecycleOwner: LifecycleOwner
) {
	Log.d("MainViewModel", "RECOMPOSITION")
	val post by viewModel.post.observeAsState(initial = Post())

	val bottomNavButtons = listOf(
		Pair("Главная", Icons.Filled.Home),
		Pair("Избранное", Icons.Filled.Favorite),
		Pair("Профиль", Icons.Filled.Person)
	)
	val selectedNavButtonIndex = remember {
		mutableIntStateOf(1)
	}

	Scaffold(
		bottomBar = {
			BottomAppBar {
				bottomNavButtons.forEachIndexed { index, pair ->
					NavigationBarItem(
						selected = index == selectedNavButtonIndex.value,
						onClick = { selectedNavButtonIndex.value = index },
						icon = { Icon(pair.second, null) },
						label = { Text(pair.first) }
					)
				}
			}
		}
	) {
		it
		PostCard(
			post = post,
			onLike = { viewModel.increaseStat(PostStatType.LIKES) },
			onRepost = { viewModel.increaseStat(PostStatType.REPOSTS) },
			onComment = { viewModel.increaseStat(PostStatType.COMMENTS) },
		)
	}
}