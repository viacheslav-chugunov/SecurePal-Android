package viach.apps.securepal.ui.screen.lock

import viach.apps.storage.model.Lock

data class LockState(
    val titleRes: Int = 0,
    val input: String = "",
    val inputSuccessful: Boolean = false,
    val errorMessageRes: Int = 0,
    val lock: Lock? = null
)