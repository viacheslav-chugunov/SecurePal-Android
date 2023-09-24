package viach.apps.securepal.ui.screen.main

import dagger.hilt.android.lifecycle.HiltViewModel
import viach.apps.securepal.StateViewModel
import viach.apps.securepal.model.SnackbarMessage
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : StateViewModel<MainState>(MainState()) {

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