package com.kaelesty.vknewsclient.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaelesty.vknewsclient.domain.entities.PostComment

@Composable
fun Comment(
	postComment: PostComment
) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(8.dp),
		elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
		shape = RoundedCornerShape(8.dp),
		colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
	) {
		Row(
			modifier = Modifier
				.fillMaxWidth()
				.padding(8.dp)
		) {
			Image(
				modifier = Modifier
					.size(50.dp)
					.background(Color.White, CircleShape)
					.padding(6.dp),
				painter = painterResource(id = postComment.avatarImageID),
				contentDescription = null
			)
			Spacer(modifier = Modifier.width(4.dp))
			Column(
				modifier = Modifier
					.weight(1f)
			) {
				Text(
					text = "[id:${postComment.id}] ${postComment.author}",
					fontSize = 10.sp,
					fontWeight = FontWeight.Bold
				)
				Text(
					text = postComment.text,
					fontSize = 14.sp,
					fontWeight = FontWeight.Light
				)
			}
		}
	}
}

@Composable
@Preview
fun PostCommentPreview() {
	VknewsclientTheme {
		Comment(postComment = PostComment(0))
	}
}