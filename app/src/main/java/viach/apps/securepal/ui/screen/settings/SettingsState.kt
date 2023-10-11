package viach.apps.securepal.ui.screen.settings

import viach.apps.storage.model.AppTheme
import viach.apps.storage.model.Lock

data class SettingsState(
    val appTheme: AppTheme = AppTheme.DEFAULT,
    val showAppThemeBottomSheet: Boolean = false,
    val showLockTypeBottomSheet: Boolean = false,
    val showLockSecretBottomSheet: Boolean = false,
    val errorMessageRes: Int = 0,
    val messageRes: Int = 0,
    val lockType: Lock.Type = Lock.DEFAULT.type,
    val selectedLockType: Lock.Type = Lock.Type.EMPTY,
)