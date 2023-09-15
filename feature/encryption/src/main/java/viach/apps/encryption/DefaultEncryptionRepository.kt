package viach.apps.encryption

import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class DefaultEncryptionRepository(
    private val encryptionToken: String
) : EncryptionRepository {
    private val secureBytes = encryptionToken.toByteArray()
    private val secret = SecretKeySpec(secureBytes, "AES")

    override fun encrypt(input: String): ByteArray =
        createCipher(Cipher.ENCRYPT_MODE).doFinal(input.toByteArray())

    override fun decrypt(input: ByteArray): String =
        try {
            String(createCipher(Cipher.DECRYPT_MODE).doFinal(input))
        } catch (e: BadPaddingException) {
            throw IllegalEncryptionTokenException(encryptionToken)
        }

    private fun createCipher(mode: Int): Cipher {
        try {
            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            val ivParams = IvParameterSpec(secureBytes)
            cipher.init(mode, secret, ivParams)
            return cipher
        } catch (e: InvalidKeyException) {
            throw IllegalEncryptionTokenException(encryptionToken)
        } catch (e: InvalidAlgorithmParameterException) {
            throw IllegalEncryptionTokenException(encryptionToken)
        }
    }
}