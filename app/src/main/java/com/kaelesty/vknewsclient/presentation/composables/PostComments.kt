package com.kaelesty.vknewsclient.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.kaelesty.vknewsclient.domain.entities.Post
import com.kaelesty.vknewsclient.domain.entities.PostComment
import com.kaelesty.vknewsclient.presentation.main.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostComments(
	post: Post,
	postComments: List<PostComment>,
	onReturn: () -> Unit,
) {

	Column(
		modifier = Modifier
			.fillMaxSize()
	) {
		TopAppBar(
			title = {
				Text(
					text = "Comments for FeedPost Id:${post.id}"
				)
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

			items(
				postComments,
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