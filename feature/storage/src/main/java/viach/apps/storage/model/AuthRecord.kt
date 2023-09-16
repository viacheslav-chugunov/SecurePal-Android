package viach.apps.storage.model

interface AuthRecord : Record {
    val auth: String
    val password: String
}