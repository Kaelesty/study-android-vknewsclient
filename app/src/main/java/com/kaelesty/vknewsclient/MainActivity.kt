package com.kaelesty.vknewsclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.kaelesty.vknewsclient.ui.theme.NewsMainScreen
import com.kaelesty.vknewsclient.ui.theme.PostCard
import com.kaelesty.vknewsclient.ui.theme.ScaffoldTest
import com.kaelesty.vknewsclient.ui.theme.VknewsclientTheme

class MainActivity : ComponentActivity() {

	private val viewModel by viewModels<MainViewModel>()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContent {
			VknewsclientTheme {
				//Box(modifier = Modifier.fillMaxSize().background(Color.Gray))
				//PostCard(post = getExamplePost())
				//ScaffoldTest()
				NewsMainScreen(viewModel, this@MainActivity)
			}
		}
	}
}