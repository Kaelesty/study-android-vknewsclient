package com.kaelesty.vknewsclient.ui.theme

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.kaelesty.vknewsclient.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun ScaffoldTest() {

	val items = listOf("Songs", "Artists", "Playlists")
	var selectedItemIndex = 2
	val snackBarHostState = remember { SnackbarHostState() }
	val scope = rememberCoroutineScope()
	val fabState = remember { mutableStateOf(true) }

	ModalNavigationDrawer(
		drawerState = rememberDrawerState(initialValue = DrawerValue.Closed),
		drawerContent = {
			ModalDrawerSheet {
				Spacer(modifier = Modifier.height((12.dp)))
				items.forEachIndexed { index, item ->
					NavigationDrawerItem(
						selected = selectedItemIndex == index,
						onClick = { selectedItemIndex = index },
						label = { Text(item) },
						icon = { Icon(Icons.Filled.Home, contentDescription = null) })
				}
			}
		},
	) {
		Scaffold(
			topBar = {
				TopAppBar(
					title = { Text("Top Bar")},
					navigationIcon = {
						IconButton(onClick = { /*TODO*/ }) {
							Icon(painter = painterResource(id = R.drawable.eye), contentDescription = null)
						}
					},
					actions = {
						IconButton(onClick = { /*TODO*/ }) {
							Icon(painter = painterResource(id = R.drawable.heart), contentDescription = null)
						}
						IconButton(onClick = { /*TODO*/ }) {
							Icon(painter = painterResource(id = R.drawable.share), contentDescription = null)
						}
						IconButton(onClick = { /*TODO*/ }) {
							Icon(painter = painterResource(id = R.drawable.comment), contentDescription = null)
						}
					}
				)
			},
			bottomBar = {
				NavigationBar {
					items.forEachIndexed { index, item ->
						NavigationBarItem(
							selected = selectedItemIndex == index,
							onClick = { selectedItemIndex = index },
							icon = { Icon(Icons.Filled.Home, contentDescription = null) })
					}
				}
			},
			floatingActionButton = {
				if (fabState.value) {
					FloatingActionButton(onClick = {
						scope.launch {
							val snackbar = snackBarHostState.showSnackbar(
								message = "This is snackbar",
								actionLabel = "Hide FAB",
								withDismissAction = true,
								duration = SnackbarDuration.Indefinite
							)
							if (snackbar == SnackbarResult.ActionPerformed) {
								fabState.value = false
							}
						}
					}) {
						Icon(Icons.Filled.Add, null)
					}
				}
			},
			snackbarHost = { SnackbarHost(snackBarHostState) },

		) {
			Text("123", modifier = Modifier.padding(it))
		}
	}
}