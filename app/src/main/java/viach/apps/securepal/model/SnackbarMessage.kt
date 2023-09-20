package viach.apps.securepal.model

sealed interface SnackbarMessage {
    class Error(val message: String) : SnackbarMessage
    class Info(val message: String) : SnackbarMessage
    object None : SnackbarMessage
}