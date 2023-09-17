package viach.apps.securepal.ui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import viach.apps.securepal.R
import viach.apps.securepal.Screen
import viach.apps.securepal.extension.navigate
import viach.apps.securepal.ui.screen.authrecord.AuthRecordScreen
import viach.apps.securepal.ui.screen.authrecord.AuthRecordViewModel
import viach.apps.securepal.ui.screen.cardrecord.CardRecordScreen
import viach.apps.securepal.ui.screen.dashboard.DashboardScreen
import viach.apps.securepal.ui.screen.dashboard.DashboardViewModel
import viach.apps.securepal.ui.screen.noterecord.NoteRecordScreen
import viach.apps.securepal.ui.screen.settings.SettingsScreen
import viach.apps.securepal.ui.theme.SecurePalTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val currentRoute = navController.currentBackStackEntryFlow.collectAsState(initial = null)
        .value?.destination?.route ?: ""

    SecurePalTheme {
        Scaffold(
            bottomBar = {
                if (currentRoute in setOf(Screen.Route.DASHBOARD, Screen.Route.SETTINGS)) {
                    BottomAppBar(
                        actions = {
                            NavigationBarItem(
                                selected = currentRoute == Screen.Route.DASHBOARD,
                                onClick = { navController.navigate(Screen.Dashboard) },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_dashboard),
                                        contentDescription = null
                                    )
                                },
                                label = {
                                    Text(
                                        text = stringResource(id = R.string.dashboard)
                                    )
                                }
                            )
                            NavigationBarItem(
                                selected = currentRoute == Screen.Route.SETTINGS,
                                onClick = { navController.navigate(Screen.Settings) },
                                icon = {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_settings),
                                        contentDescription = null
                                    )
                                },
                                label = {
                                    Text(
                                        text = stringResource(id = R.string.settings)
                                    )
                                }
                            )
                        },
                        floatingActionButton = {
                            FloatingActionButton(onClick = {  }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_add),
                                    contentDescription = null
                                )
                            }
                        }
                    )
                }
            }
        ) { padding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = padding.calculateBottomPadding()),
                color = MaterialTheme.colorScheme.background
            ) {
                NavHost(
                    navController = navController,
                    startDestination = Screen.Route.DASHBOARD
                ) {
                    composable(Screen.Route.DASHBOARD) {
                        val viewModel = hiltViewModel<DashboardViewModel>()
                        DashboardScreen(
                            state = viewModel.stateFlow.collectAsState().value,
                            onAction = viewModel::handle,
                            openScreen = navController::navigate
                        )
                    }
                    composable(Screen.Route.SETTINGS) {
                        SettingsScreen()
                    }
                    composable(Screen.Route.AUTH_RECORD) {
                        val viewModel = hiltViewModel<AuthRecordViewModel>()
                        AuthRecordScreen(
                            state = viewModel.stateFlow.collectAsState().value,
                            onAction = viewModel::handle,
                            navigateBack = navController::popBackStack
                        )
                    }
                    composable(Screen.Route.NOTE_RECORD) {
                        NoteRecordScreen()
                    }
                    composable(Screen.Route.CARD_RECORD) {
                        CardRecordScreen()
                    }
                    composable(Screen.Route.SHOW_AUTH_RECORD) {

                    }
                    composable(Screen.Route.SHOW_NOTE_RECORD) {

                    }
                    composable(Screen.Route.SHOW_CARD_RECORD) {

                    }
                }
            }
        }
    }
}