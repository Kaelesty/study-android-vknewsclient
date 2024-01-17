package com.kaelesty.vknewsclient.presentation.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostComment
import com.kaelesty.vknewsclient.presentation.main.CommentsViewModel
import com.kaelesty.vknewsclient.presentation.main.CommentsViewModelFactory
import com.kaelesty.vknewsclient.presentation.states.CommentsState

@Composable
fun PostComments(
	onReturn: () -> Unit,
	post: Post
) {

	val viewModel: CommentsViewModel = viewModel(
		factory = CommentsViewModelFactory(post)
	)

	val state by viewModel.commentsState.observeAsState()

	when (state) {
		is CommentsState.Comments -> {
			PostCommentsDefault(
				state as CommentsState.Comments,
				onReturn,
				post
			)
		}

		else -> {}
	}
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun PostCommentsDefault(
	commentsState: CommentsState.Comments,
	onReturn: () -> Unit,
	post: Post
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
	) {
		TopAppBar(
			title = {
					Text(text = "Comments")
			},
			navigationIcon = {
				IconButton(onClick = { onReturn() }) {
					Icon(
						imageVector = Icons.Outlined.ArrowBack,
						contentDescription = "Back arrow"
					)
				}
			}
		)

		LazyColumn {
			stickyHeader {
				PostCard(
					post = post,
					onLike = { /*TODO*/ },
					onRepost = { /*TODO*/ },
					onComment = null

				)
			}
			items(
				commentsState.comments,
				key = { it.id },
			) {
				Comment(
					postComment = it
				)
			}
		}
	}
}

//
//@Composable
//@Preview
//fun PostCommentPreviewLight() {
//	VknewsclientTheme(
//		darkTheme = false
//	) {
//		PostComments(
//			post = Post(
//				id = 0,
//			),
//			postComments = MainViewModel().getPostComments(0)
//		)
//	}
//}
//
//@Composable
//@Preview
//fun PostCommentPreviewDark() {
//	VknewsclientTheme(
//		darkTheme = true
//	) {
//		PostComments(
//			post = Post(
//				id = 0,
//			),
//			postComments = MainViewModel().getPostComments(0)
//		)
//	}
//}