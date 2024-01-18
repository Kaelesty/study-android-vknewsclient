package com.kaelesty.vknewsclient.presentation.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kaelesty.vknewsclient.R
import com.kaelesty.vknewsclient.presentation.main.MainViewModel

@Composable
fun LoginScreen(
	loginCallback: () -> Unit,
) {

	Column(
		modifier = Modifier
			.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		Image(
			painter = painterResource(id = R.drawable.vk_com_logo),
			contentDescription = "vk logo",
			modifier = Modifier
				.weight(1f)
				.size(250.dp)
		)
		Button(
			onClick = { loginCallback() },
			modifier = Modifier
				.width(300.dp)
				.padding(25.dp)
			,
			shape = RoundedCornerShape(8.dp),
			colors = ButtonDefaults.buttonColors(
				containerColor = DarkBlue,
				contentColor = Color.White
			)
		) {
			Text(stringResource(R.string.login))
		}
	}
}

@Composable
@Preview
fun PreviewLoginScreen() {
	LoginScreen(
		{}
	)
}