package viach.apps.securepal.ui.screen.main

import dagger.hilt.android.lifecycle.HiltViewModel
import viach.apps.securepal.StateViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : StateViewModel<MainState>(MainState()) {

    fun handle(action: MainAction) {
        when (action) {
            MainAction.HandleSnackbarMessage -> {
                state = state.copy(snackbarErrorMessage = "")
            }
            is MainAction.ShowSnackbarError -> {
                state = state.copy(snackbarErrorMessage = action.message)
            }
        }
    }

}