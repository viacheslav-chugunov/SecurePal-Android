package viach.apps.encryption

import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class DefaultEncryptionRepository(secureToken: String) : EncryptionRepository {
    private val secureBytes = secureToken.toByteArray()
    private val secret = SecretKeySpec(secureBytes, "AES")
    private val ivParams = IvParameterSpec(secureBytes)
    private val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")

    override fun encrypt(input: String): ByteArray {
        cipher.init(Cipher.ENCRYPT_MODE, secret, ivParams)
        return cipher.doFinal(input.toByteArray())
    }

    override fun decrypt(input: ByteArray): String {
        cipher.init(Cipher.DECRYPT_MODE, secret, ivParams)
        return String(cipher.doFinal(input))
    }
}