package viach.apps.securepal.ui.screen.settings

import viach.apps.storage.model.AppTheme

data class SettingsState(
    val appTheme: AppTheme = AppTheme.DEFAULT,
    val showAppThemeBottomSheet: Boolean = false
)