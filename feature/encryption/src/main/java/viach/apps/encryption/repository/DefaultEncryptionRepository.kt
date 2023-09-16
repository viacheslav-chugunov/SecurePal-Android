package viach.apps.encryption.repository

import viach.apps.encryption.IllegalEncryptionTokenException
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

internal class DefaultEncryptionRepository(
    private val encryptionToken: String
) : EncryptionRepository {
    private val secureBytes = encryptionToken.toByteArray()
    private val secret = SecretKeySpec(secureBytes, "AES")

    override fun encrypt(input: String): String =
        createCipher(Cipher.ENCRYPT_MODE).doFinal(input.toByteArray()).joinToString(" ")

    override fun decrypt(input: String): String =
        try {
            val byteArray = input.split(" ").map { it.toByte() }.toByteArray()
            String(createCipher(Cipher.DECRYPT_MODE).doFinal(byteArray))
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