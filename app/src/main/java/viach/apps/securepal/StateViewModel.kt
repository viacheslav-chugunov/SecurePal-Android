package viach.apps.securepal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class StateViewModel<State>(initialModel: State) : ViewModel() {
    protected var state: State = initialModel
        set(value) {
            mutableStateFlow.value = value
            field = value
        }
    private val mutableStateFlow = MutableStateFlow(state)
    val stateFlow = mutableStateFlow.asStateFlow()
}