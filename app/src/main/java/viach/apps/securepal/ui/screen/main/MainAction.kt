package viach.apps.securepal.ui.screen.main

sealed interface MainAction {
    class ShowSnackbarError(val message: String) : MainAction
    object HandleSnackbarMessage : MainAction
}