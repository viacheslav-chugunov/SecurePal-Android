package viach.apps.encryption.repository

interface EncryptionRepository {
    fun encrypt(input: String): String
    fun decrypt(input: String): String
}