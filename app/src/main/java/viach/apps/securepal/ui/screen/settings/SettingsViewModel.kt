package viach.apps.securepal.ui.screen.settings

import android.util.Log
import androidx.core.net.toFile
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viach.apps.export.repository.ExportRepository
import viach.apps.securepal.R
import viach.apps.securepal.StateViewModel
import viach.apps.storage.model.TypedAppSetting
import viach.apps.storage.repository.AppSettingsRepository
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appSettingsRepository: AppSettingsRepository,
    private val exportRepository: ExportRepository
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
            SettingsAction.Export -> {
                viewModelScope.launch(Dispatchers.IO) {
                    val success = exportRepository.export()
                    state = if (success) {
                        state.copy(messageRes = R.string.export_file_successfully_saved)
                    } else {
                        state.copy(errorMessageRes = R.string.export_file_saving_error)
                    }
                }
            }
            is SettingsAction.Import -> {
                viewModelScope.launch(Dispatchers.IO) {
                    exportRepository.import(action.uri)
                }
            }
            SettingsAction.HandleErrorMessage -> {
                state = state.copy(errorMessageRes = 0)
            }
            SettingsAction.HandleMessage -> {
                state = state.copy(messageRes = 0)
            }
        }
    }

}