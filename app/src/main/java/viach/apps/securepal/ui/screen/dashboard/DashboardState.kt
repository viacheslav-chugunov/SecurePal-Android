package viach.apps.securepal.ui.screen.dashboard

import viach.apps.securepal.Screen
import viach.apps.storage.model.AuthRecord
import viach.apps.storage.model.CardRecord
import viach.apps.storage.model.NoteRecord

data class DashboardState(
    val authRecords: List<AuthRecord> = emptyList(),
    val cardRecords: List<CardRecord> = emptyList(),
    val noteRecords: List<NoteRecord> = emptyList(),
    val openScreen: Screen? = null
)