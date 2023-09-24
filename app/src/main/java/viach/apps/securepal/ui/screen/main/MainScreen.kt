package viach.apps.securepal.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import viach.apps.securepal.Screen
import viach.apps.securepal.extension.getParcelable
import viach.apps.securepal.extension.navigate
import viach.apps.securepal.model.AuthRecordParcelable
import viach.apps.securepal.model.CardRecordParcelable
import viach.apps.securepal.model.NoteRecordParcelable
import viach.apps.securepal.model.SnackbarMessage
import viach.apps.securepal.ui.screen.authrecord.AuthRecordScreen
import viach.apps.securepal.ui.screen.authrecord.AuthRecordViewModel
import viach.apps.securepal.ui.screen.cardrecord.CardRecordScreen
import viach.apps.securepal.ui.screen.cardrecord.CardRecordViewModel
import viach.apps.securepal.ui.screen.dashboard.DashboardScreen
import viach.apps.securepal.ui.screen.dashboard.DashboardViewModel
import viach.apps.securepal.ui.screen.noterecord.NoteRecordScreen
import viach.apps.securepal.ui.screen.noterecord.NoteRecordViewModel
import viach.apps.securepal.ui.screen.settings.SettingsScreen
import viach.apps.securepal.ui.screen.showauthrecord.ShowAuthRecordScreen
import viach.apps.securepal.ui.screen.showauthrecord.ShowAuthRecordViewModel
import viach.apps.securepal.ui.screen.showcardrecord.ShowCardRecordScreen
import viach.apps.securepal.ui.screen.showcardrecord.ShowCardRecordViewModel
import viach.apps.securepal.ui.screen.shownoterecord.ShowNoteRecordScreen
import viach.apps.securepal.ui.screen.shownoterecord.ShowNoteRecordViewModel
import viach.apps.securepal.ui.theme.SecurePalTheme
import viach.apps.securepal.ui.view.MainBottomAppBar
import viach.apps.securepal.ui.view.MainBottomSheet

@Composable
fun MainScreen() {
    val mainViewModel = hiltViewModel<MainViewModel>()
    val state = mainViewModel.stateFlow.collectAsState().value
    val navController = rememberNavController()
    val errorSnackbarHostState = SnackbarHostState()
    val infoSnackbarHostState = SnackbarHostState()
    val currentRoute = navController.currentBackStackEntryFlow.collectAsState(initial = null)
        .value?.destination?.route ?: ""

    LaunchedEffect(state.snackbarMessage) {
        when (state.snackbarMessage) {
            is SnackbarMessage.Error -> {
                errorSnackbarHostState.showSnackbar(
                    message = state.snackbarMessage.message,
                    duration = SnackbarDuration.Short
                )
                mainViewModel.handle(MainAction.HandleSnackbarMessage)
            }
            is SnackbarMessage.Info -> {
                infoSnackbarHostState.showSnackbar(
                    message = state.snackbarMessage.message,
                    duration = SnackbarDuration.Short
                )
                mainViewModel.handle(MainAction.HandleSnackbarMessage)
            }
            SnackbarMessage.None -> {}
        }
    }

    SecurePalTheme {
        Box {
            Scaffold(
                bottomBar = {
                    MainBottomAppBar(
                        currentRoute = currentRoute,
                        onClickDashboard = { navController.navigate(Screen.Dashboard)},
                        onClickSettings = { navController.navigate(Screen.Settings) },
                        onClickAdd = { mainViewModel.handle(MainAction.ShowBottomSheet(true)) }
                    )
                },
                snackbarHost = {
                    SnackbarHost(hostState = errorSnackbarHostState) {
                        Snackbar(
                            snackbarData = it,
                            modifier = Modifier.fillMaxWidth(),
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                            contentColor = MaterialTheme.colorScheme.onErrorContainer
                        )
                    }
                    SnackbarHost(hostState = infoSnackbarHostState) {
                        Snackbar(
                            snackbarData = it,
                            modifier = Modifier.fillMaxWidth()
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
                            val authRecord = navController.getParcelable<AuthRecordParcelable>(Screen.EditAuthRecord.Key.AUTH_RECORD)
                            viewModel.setEditableAuthRecord(authRecord)
                            AuthRecordScreen(
                                state = viewModel.stateFlow.collectAsState().value,
                                onAction = viewModel::handle,
                                navigateBack = navController::popBackStack,
                                showError = { mainViewModel.handle(MainAction.ShowSnackbar(SnackbarMessage.Error(it))) }
                            )
                        }
                        composable(Screen.Route.NOTE_RECORD) {
                            val viewModel = hiltViewModel<NoteRecordViewModel>()
                            val noteRecord = navController.getParcelable<NoteRecordParcelable>(Screen.EditNoteRecord.Key.NOTE_RECORD)
                            viewModel.setEditableNoteRecord(noteRecord)
                            NoteRecordScreen(
                                state = viewModel.stateFlow.collectAsState().value,
                                onAction = viewModel::handle,
                                navigateBack = navController::popBackStack,
                                showError = { mainViewModel.handle(MainAction.ShowSnackbar(SnackbarMessage.Error(it))) }
                            )
                        }
                        composable(Screen.Route.CARD_RECORD) {
                            val viewModel = hiltViewModel<CardRecordViewModel>()
                            val cardRecord = navController.getParcelable<CardRecordParcelable>(Screen.EditCardRecord.Key.CARD_RECORD)
                            viewModel.setEditableCardRecord(cardRecord)
                            CardRecordScreen(
                                state = viewModel.stateFlow.collectAsState().value,
                                onAction = viewModel::handle,
                                navigateBack = navController::popBackStack,
                                showError = { mainViewModel.handle(MainAction.ShowSnackbar(SnackbarMessage.Error(it))) }
                            )
                        }
                        composable(Screen.Route.SHOW_AUTH_RECORD) {
                            val viewModel = hiltViewModel<ShowAuthRecordViewModel>()
                            val authRecord = navController.getParcelable<AuthRecordParcelable>(Screen.ShowAuthRecord.Key.AUTH_RECORD)
                            viewModel.setAuthRecord(authRecord)
                            ShowAuthRecordScreen(
                                state = viewModel.stateFlow.collectAsState().value,
                                onAction = viewModel::handle,
                                openScreen = navController::navigate,
                                navigateBack = navController::popBackStack,
                                showMessage = { mainViewModel.handle(MainAction.ShowSnackbar(SnackbarMessage.Info(it))) }
                            )
                        }
                        composable(Screen.Route.SHOW_NOTE_RECORD) {
                            val viewModel = hiltViewModel<ShowNoteRecordViewModel>()
                            val noteRecord = navController.getParcelable<NoteRecordParcelable>(Screen.ShowNoteRecord.Key.NOTE_RECORD)
                            viewModel.setNoteRecord(noteRecord)
                            ShowNoteRecordScreen(
                                state = viewModel.stateFlow.collectAsState().value,
                                onAction = viewModel::handle,
                                openScreen = navController::navigate,
                                navigateBack = navController::popBackStack,
                                showMessage = { mainViewModel.handle(MainAction.ShowSnackbar(SnackbarMessage.Info(it))) }
                            )
                        }
                        composable(Screen.Route.SHOW_CARD_RECORD) {
                            val viewModel = hiltViewModel<ShowCardRecordViewModel>()
                            val cardRecord = navController.getParcelable<CardRecordParcelable>(Screen.ShowCardRecord.Key.CARD_RECORD)
                            viewModel.setCardRecord(cardRecord)
                            ShowCardRecordScreen(
                                state = viewModel.stateFlow.collectAsState().value,
                                onAction = viewModel::handle,
                                openScreen = navController::navigate,
                                navigateBack = navController::popBackStack,
                                showMessage = { mainViewModel.handle(MainAction.ShowSnackbar(SnackbarMessage.Info(it))) }
                            )
                        }
                    }
                }
            }
            MainBottomSheet(
                show = state.showBottomSheet,
                onDismiss = { mainViewModel.handle(MainAction.ShowBottomSheet(false)) },
                onClickAddAuth = { navController.navigate(Screen.NewAuthRecord) },
                onClickAddNote = { navController.navigate(Screen.NewNoteRecord) },
                onClickAddCard = { navController.navigate(Screen.NewCardRecord) }
            )
        }
    }
}