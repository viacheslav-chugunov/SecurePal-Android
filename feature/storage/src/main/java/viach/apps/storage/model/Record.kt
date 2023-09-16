package viach.apps.storage.model

sealed interface Record {
    val title: String
    val note: String
    val createdAt: Long
    val updatedAt: Long
}