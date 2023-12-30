package com.kaelesty.vknewsclient.presentation.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostStatType
import com.kaelesty.vknewsclient.presentation.main.MainViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun NewsFeed(
	paddingValues: PaddingValues,
	posts: List<Post>,
	viewModel: MainViewModel
) {
	LazyColumn(
		modifier = Modifier.padding(paddingValues)
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
					Box(
						modifier = Modifier
							.padding(20.dp)
							.background(
								Color.Red,
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