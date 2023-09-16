package viach.apps.storage.model

interface CardRecord : Record {
    val owner: String
    val number: String
    val check: String
    val pin: String
    val expiration: String
}