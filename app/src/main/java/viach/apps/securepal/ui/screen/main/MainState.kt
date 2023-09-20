package viach.apps.securepal.ui.screen.main

import viach.apps.securepal.model.SnackbarMessage

data class MainState(
    val snackbarMessage: SnackbarMessage = SnackbarMessage.None,
)