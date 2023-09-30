package viach.apps.securepal.ui.screen.main

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import viach.apps.securepal.StateViewModel
import viach.apps.securepal.model.SnackbarMessage
import viach.apps.storage.model.AppTheme
import viach.apps.storage.model.TypedAppSetting
import viach.apps.storage.repository.AppSettingsRepository
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appSettingsRepository: AppSettingsRepository
) : StateViewModel<MainState>(MainState()) {

    init {
        observeAppSettings()
    }

    private fun observeAppSettings() {
        viewModelScope.launch(Dispatchers.IO) {
            appSettingsRepository.getAll().collect { typedSettings ->
                typedSettings.forEach { typedSetting ->
                    when (typedSetting) {
                        is TypedAppSetting.AppTheme -> {
                            state = state.copy(appTheme = typedSetting.appTheme)
                        }
                    }
                }
            }
        }
    }

    fun handle(action: MainAction) {
        when (action) {
            MainAction.HandleSnackbarMessage -> {
                state = state.copy(snackbarMessage = SnackbarMessage.None)
            }
            is MainAction.ShowSnackbar -> {
                state = state.copy(snackbarMessage = action.message)
            }
            is MainAction.ShowBottomSheet -> {
                state = state.copy(showBottomSheet = action.show)
            }
        }
    }

}