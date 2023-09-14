package viach.apps.encryption

interface EncryptionRepository {
    fun encrypt(input: String): ByteArray
    fun decrypt(input: ByteArray): String
}