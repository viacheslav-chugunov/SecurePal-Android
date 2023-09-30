package viach.apps.securepal.ui.screen.settings

import viach.apps.storage.model.AppTheme

sealed interface SettingsAction {
    class SetAppTheme(val appTheme: AppTheme): SettingsAction
    class ShowAppThemeBottomSheet(val show: Boolean): SettingsAction
}