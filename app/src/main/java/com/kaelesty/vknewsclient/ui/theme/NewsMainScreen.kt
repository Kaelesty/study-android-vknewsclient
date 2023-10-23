package com.kaelesty.vknewsclient.ui.theme

import android.app.Activity
import android.graphics.Color
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleOwner
import com.kaelesty.vknewsclient.MainViewModel
import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostStatType

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NewsMainScreen(
	viewModel: MainViewModel,
) {
	val posts by viewModel.posts.observeAsState(initial = mutableListOf())

	val bottomNavButtons = listOf(
		Pair("Главная", Icons.Filled.Home),
		Pair("Избранное", Icons.Filled.Favorite),
		Pair("Профиль", Icons.Filled.Person)
	)
	val selectedNavButtonIndex = remember {
		mutableIntStateOf(0)
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
		LazyColumn(
			modifier = Modifier.padding(it)
		) {
			items(posts, key = { it.id }) {
				val dismissState = rememberDismissState()

				if (dismissState.isDismissed(DismissDirection.EndToStart)) {
					viewModel.delItem(it.id)
				}

				SwipeToDismiss(
					modifier = Modifier.animateItemPlacement(),
					state = dismissState,
					background = {
								 Box(modifier = Modifier
									 .padding(20.dp)
									 .background(
										 androidx.compose.ui.graphics.Color.Red,
										 RoundedCornerShape(4.dp)
									 )
									 .fillMaxSize(),
									 contentAlignment = Alignment.CenterEnd
								 ) {
									 Text(
										 text = "DELETE",
										 fontSize = 20.sp,
										 modifier = Modifier.padding(16.dp)
									 )
								 }
					},
					dismissContent = {
						PostCard(
							post = it,
							onLike = { viewModel.increaseStat(it.id, PostStatType.LIKES) },
							onRepost = { viewModel.increaseStat(it.id, PostStatType.REPOSTS) },
							onComment = { viewModel.increaseStat(it.id, PostStatType.COMMENTS) }
						)
					},
					directions = setOf(DismissDirection.EndToStart)
				)
			}
		}
	}
}