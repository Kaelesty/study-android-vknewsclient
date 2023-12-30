package com.kaelesty.vknewsclient.presentation.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kaelesty.vknewsclient.R
import com.kaelesty.vknewsclient.domain.entities.Post

@Composable
fun PostCard(
	post: Post,
	onLike: () -> Unit,
	onRepost: () -> Unit,
	onComment: () -> Unit,
) {
	Card(
		modifier = Modifier
			.fillMaxWidth()
			.padding(8.dp),
		elevation = CardDefaults.elevatedCardElevation(defaultElevation = 4.dp),
		shape = RoundedCornerShape(8.dp),
		colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.background)
	) {
		Column(Modifier.padding(8.dp)) {

			Row(
				Modifier
					.fillMaxWidth()
					.padding(horizontal = 8.dp),
				horizontalArrangement = Arrangement.SpaceEvenly,
				verticalAlignment = Alignment.CenterVertically
			) {
				Image(
					painterResource(id = post.content.groupAvatarId),
					contentDescription = "Group Avatar",
					modifier = Modifier
						.size(50.dp)
						.background(Color.White, CircleShape)
						.padding(6.dp),
				)
				Spacer(modifier = Modifier.width(4.dp))
				Column(
					verticalArrangement = Arrangement.Center,
					modifier = Modifier
						.height(65.dp)
						.weight(1f)
				) {
					Text(
						post.content.groupName,
						fontSize = 16.sp,
						fontWeight = FontWeight.ExtraBold,
					)
					Spacer(modifier = Modifier.height(2.dp))
					Text(post.content.time, fontSize = 10.sp)
				}
				Box(
					contentAlignment = Alignment.CenterEnd
				) {
					IconButton(
						onClick = { /*TODO*/ },
						modifier = Modifier
							.size(30.dp)
					) {
						Icon(
							painterResource(id = R.drawable.dots),
							contentDescription = "Dots",
							tint = MaterialTheme.colorScheme.onBackground
						)
					}
				}
			}
			Text(
				text = post.content.text,
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 8.dp),
			)
			Image(
				painter = painterResource(id = post.content.imageId),
				contentDescription = "Post image",
				modifier = Modifier
					.fillMaxWidth()
					.padding(8.dp)
					.clip(RoundedCornerShape(4.dp))
					.border(1.dp, MaterialTheme.colorScheme.onBackground, RoundedCornerShape(4.dp)),
				contentScale = ContentScale.FillWidth
			)
			Row(
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 10.dp)
					.height(20.dp)
			) {
				Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterStart) {
					StatWithIcon(value = post.statistics.watchers, iconId = R.drawable.eye)
				}
				StatWithIcon(
					value = post.statistics.reposts,
					iconId = R.drawable.share,
					onClick = onRepost,
				)
				StatWithIcon(
					value = post.statistics.comments,
					iconId = R.drawable.comment,
					onClick = onComment,
				)
				StatWithIcon(
					value = post.statistics.likes,
					iconId = R.drawable.heart,
					hasEndPadding = false,
					onClick = onLike,
				)
			}
		}
	}
}

@Composable
fun StatWithIcon(
	value: Int,
	iconId: Int,
	hasEndPadding: Boolean = true,
	onClick: () -> Unit = {}
) {
	Log.d("MainViewModel", "RECOMPOSITION STAT")
	Row(
		verticalAlignment = Alignment.CenterVertically,
		modifier = Modifier
			.padding(end = if (hasEndPadding) 8.dp else 0.dp)
			.clickable { onClick() }

	) {
		Text(value.toString())
		Icon(
			painterResource(id = iconId),
			contentDescription = null,
			tint = MaterialTheme.colorScheme.onBackground,
			modifier = Modifier
				.padding(top = 3.dp)
				.size(20.dp)
		)
	}
}

//@Preview
//@Composable
//fun PostPreviewLight() {
//	VknewsclientTheme(darkTheme = false) {
//		PostCard(post = getExamplePost())
//	}
//}
//
//@Preview
//@Composable
//fun PostPreviewDark() {
//	VknewsclientTheme(darkTheme = true) {
//		PostCard(post = getExamplePost())
//	}
//}
