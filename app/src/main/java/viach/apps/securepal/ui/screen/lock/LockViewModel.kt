package viach.apps.securepal.ui.screen.lock

import dagger.hilt.android.lifecycle.HiltViewModel
import viach.apps.securepal.R
import viach.apps.securepal.StateViewModel
import javax.inject.Inject

@HiltViewModel
class LockViewModel @Inject constructor() : StateViewModel<LockState>(LockState()) {

    init {
        state = state.copy(titleRes = R.string.enter_pin_to_unlock_securepal)
    }

    fun handle(action: LockAction) {
        when (action) {
            LockAction.SendInput -> {
                state = state.copy(inputSuccessful = true)
            }
            is LockAction.UpdateInput -> {
                state = state.copy(input = action.input)
            }
        }
    }

}