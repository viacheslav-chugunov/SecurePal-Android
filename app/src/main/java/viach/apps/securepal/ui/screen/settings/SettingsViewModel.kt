package viach.apps.securepal.ui.screen.settings

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viach.apps.securepal.StateViewModel
import viach.apps.storage.model.TypedAppSetting
import viach.apps.storage.repository.AppSettingsRepository
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appSettingsRepository: AppSettingsRepository
) : StateViewModel<SettingsState>(SettingsState()) {

    init {
        observeAppSettings()
    }

    private fun observeAppSettings() {
        viewModelScope.launch(Dispatchers.IO) {
            appSettingsRepository.getAll().collect { typedSettings ->
                var appTheme = state.appTheme
                typedSettings.forEach { typedSetting ->
                    when (typedSetting) {
                        is TypedAppSetting.AppTheme -> {
                            appTheme = typedSetting.appTheme
                        }
                    }
                }
                state = state.copy(appTheme = appTheme)
            }
        }
    }

    fun handle(action: SettingsAction) {
        when (action) {
            is SettingsAction.SetAppTheme -> {
                viewModelScope.launch(Dispatchers.IO) {
                    appSettingsRepository.setTheme(action.appTheme)
                }
            }
            is SettingsAction.ShowAppThemeBottomSheet -> {
                state = state.copy(showAppThemeBottomSheet = action.show)
            }
        }
    }

}