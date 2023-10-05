package viach.apps.securepal.ui.screen.settings

import android.net.Uri
import viach.apps.storage.model.AppTheme

sealed interface SettingsAction {
    class SetAppTheme(val appTheme: AppTheme): SettingsAction
    class ShowAppThemeBottomSheet(val show: Boolean): SettingsAction
    object Export : SettingsAction
    class Import(val uri: Uri) : SettingsAction
    object HandleErrorMessage : SettingsAction
    object HandleMessage : SettingsAction
}