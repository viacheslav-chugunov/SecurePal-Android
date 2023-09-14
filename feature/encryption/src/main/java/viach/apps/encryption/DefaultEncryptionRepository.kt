package viach.apps.encryption

import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class DefaultEncryptionRepository : EncryptionRepository {
    private val secret = SecretKeySpec("encryption-key".toByteArray(), "AES")
    private val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")

    override fun encrypt(input: String): ByteArray {
        cipher.init(Cipher.ENCRYPT_MODE, secret)
        return cipher.doFinal(input.toByteArray())
    }

    override fun decrypt(input: ByteArray): String {
        cipher.init(Cipher.DECRYPT_MODE, secret)
        return String(cipher.doFinal(input))
    }
}