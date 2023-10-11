package viach.apps.securepal.ui.screen.lock

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import viach.apps.lock.repository.LockRepository
import viach.apps.securepal.R
import viach.apps.securepal.StateViewModel
import viach.apps.storage.model.Lock
import javax.inject.Inject

@HiltViewModel
class LockViewModel @Inject constructor(
    private val lockRepository: LockRepository
) : StateViewModel<LockState>(LockState()) {

    init {
        observeLock()
    }

    private fun observeLock() {
        viewModelScope.launch(Dispatchers.IO) {
            lockRepository.getLock().collect { lock ->
                val titleRes = when (lock.type) {
                    Lock.Type.EMPTY -> 0
                    Lock.Type.PIN -> R.string.enter_pin_to_unlock_securepal
                    Lock.Type.PASSWORD -> R.string.enter_password_to_unlock_securepal
                }
                state = state.copy(lock = lock, titleRes = titleRes)
            }
        }
    }

    fun handle(action: LockAction) {
        when (action) {
            LockAction.SendInput -> {
                state.lock?.let { lock ->
                    val isValid = lock.validate(state.input)
                    state = if (isValid) {
                        state.copy(inputSuccessful = true)
                    } else {
                        val errorMessageRes = when (lock.type) {
                            Lock.Type.EMPTY -> 0
                            Lock.Type.PIN -> R.string.invalid_pin
                            Lock.Type.PASSWORD -> R.string.invalid_password
                        }
                        state.copy(errorMessageRes = errorMessageRes)
                    }
                }
            }
            is LockAction.UpdateInput -> {
                state = state.copy(input = action.input)
            }
            LockAction.HandleErrorMessage -> {
                state = state.copy(errorMessageRes = 0)
            }
        }
    }

}