package viach.apps.securepal.ui.screen.main

import viach.apps.securepal.model.SnackbarMessage
import viach.apps.storage.model.AppTheme

data class MainState(
    val snackbarMessage: SnackbarMessage = SnackbarMessage.None,
    val showBottomSheet: Boolean = false,
    val appTheme: AppTheme = AppTheme.DEFAULT
)