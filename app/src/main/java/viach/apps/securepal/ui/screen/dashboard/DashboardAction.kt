package viach.apps.securepal.ui.screen.dashboard

import viach.apps.securepal.Screen
import viach.apps.storage.model.AuthRecord
import viach.apps.storage.model.CardRecord
import viach.apps.storage.model.NoteRecord

sealed interface DashboardAction {
    class OpenScreen(val screen: Screen) : DashboardAction
    object HandleOpenedScreen : DashboardAction
}