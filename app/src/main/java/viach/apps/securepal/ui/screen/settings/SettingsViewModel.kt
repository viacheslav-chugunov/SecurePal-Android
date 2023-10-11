package viach.apps.securepal.ui.screen.settings

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viach.apps.export.repository.ExportRepository
import viach.apps.lock.repository.LockRepository
import viach.apps.securepal.R
import viach.apps.securepal.StateViewModel
import viach.apps.storage.model.Lock
import viach.apps.storage.model.TypedAppSetting
import viach.apps.storage.repository.AppSettingsRepository
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val appSettingsRepository: AppSettingsRepository,
    private val exportRepository: ExportRepository,
    private val lockRepository: LockRepository
) : StateViewModel<SettingsState>(SettingsState()) {

    init {
        observeAppSettings()
    }

    private fun observeAppSettings() {
        viewModelScope.launch(Dispatchers.IO) {
            appSettingsRepository.getAll().collect { typedSettings ->
                var appTheme = state.appTheme
                var lockType = state.lockType
                typedSettings.forEach { typedSetting ->
                    when (typedSetting) {
                        is TypedAppSetting.AppTheme -> {
                            appTheme = typedSetting.appTheme
                        }
                        is TypedAppSetting.Lock -> {
                            lockType = typedSetting.lock.type
                        }
                    }
                }
                state = state.copy(appTheme = appTheme, lockType = lockType)
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
                    val success = exportRepository.import(action.uri)
                    state = if (success) {
                        state.copy(messageRes = R.string.app_data_successfully_imported)
                    } else {
                        state.copy(errorMessageRes = R.string.import_file_error)
                    }
                }
            }
            SettingsAction.HandleErrorMessage -> {
                state = state.copy(errorMessageRes = 0)
            }
            SettingsAction.HandleMessage -> {
                state = state.copy(messageRes = 0)
            }
            is SettingsAction.SetLockSecret -> {
                if (action.secret.isEmpty()) {
                    state = state.copy(errorMessageRes = when (state.selectedLockType) {
                        Lock.Type.EMPTY -> 0
                        Lock.Type.PIN -> R.string.pin_must_not_be_empty
                        Lock.Type.PASSWORD -> R.string.password_must_not_be_empty
                    })
                } else if (action.secret != action.confirm) {
                    state = state.copy(errorMessageRes = when (state.selectedLockType) {
                        Lock.Type.EMPTY -> 0
                        Lock.Type.PIN -> R.string.pin_mismatch
                        Lock.Type.PASSWORD -> R.string.password_mismatch
                    })
                } else {
                    viewModelScope.launch(Dispatchers.IO) {
                        lockRepository.setLock(Lock(action.secret, state.selectedLockType))
                    }
                }
            }
            is SettingsAction.SetLockType -> {
                state = state.copy(selectedLockType = action.type, showLockSecretBottomSheet = true)
            }
            is SettingsAction.ShowLockTypeBottomSheet -> {
                state = state.copy(showLockTypeBottomSheet = action.show)
            }
        }
    }

}