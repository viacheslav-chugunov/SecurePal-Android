package viach.apps.securepal.ui.screen.settings

import android.net.Uri
import viach.apps.storage.model.AppTheme
import viach.apps.storage.model.Lock

sealed interface SettingsAction {
    class SetAppTheme(val appTheme: AppTheme): SettingsAction
    class ShowAppThemeBottomSheet(val show: Boolean): SettingsAction
    object Export : SettingsAction
    class Import(val uri: Uri) : SettingsAction
    object HandleErrorMessage : SettingsAction
    object HandleMessage : SettingsAction
    class ShowLockTypeBottomSheet(val show: Boolean): SettingsAction
    class SetLockType(val type: Lock.Type) : SettingsAction
    class SetLockSecret(val secret: String, val confirm: String): SettingsAction
}