package viach.apps.securepal.ui.screen.main

import viach.apps.securepal.model.SnackbarMessage

sealed interface MainAction {
    class ShowSnackbar(val message: SnackbarMessage) : MainAction
    object HandleSnackbarMessage : MainAction
}