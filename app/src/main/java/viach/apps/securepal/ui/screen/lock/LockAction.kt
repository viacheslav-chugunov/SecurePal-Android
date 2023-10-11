package viach.apps.securepal.ui.screen.lock

sealed interface LockAction {
    class UpdateInput(val input: String) : LockAction
    object SendInput : LockAction
    object HandleErrorMessage : LockAction
}