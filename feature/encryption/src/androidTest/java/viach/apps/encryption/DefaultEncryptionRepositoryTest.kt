package viach.apps.encryption

import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DefaultEncryptionRepositoryTest {

    @Test(expected = IllegalEncryptionTokenException::class)
    fun throwOnIllegalToken() {
        val repository = DefaultEncryptionRepository("1234")
        repository.encrypt("A message")
    }

    @Test
    fun correctToken() {
        val repository = DefaultEncryptionRepository("1234123412341234")
        repository.encrypt("A message")
    }

    @Test
    fun encryptAndDecrypt() {
        val repository = DefaultEncryptionRepository("1234123412341234")
        val message = "A message to encrypt and decrypt"
        val encrypted = repository.encrypt(message)
        val decrypted = repository.decrypt(encrypted)
        Assert.assertEquals(message, decrypted)
    }

    @Test
    fun differentEncryptedByteArraysIfTokensAreDifferent() {
        val repository1 = DefaultEncryptionRepository("1234123412341234")
        val repository2 = DefaultEncryptionRepository("4321432143214321")
        val message = "A message to encrypt"
        val encrypted1 = repository1.encrypt(message)
        val encrypted2 = repository2.encrypt(message)
        Assert.assertNotEquals(encrypted1.contentToString(), encrypted2.contentToString())
    }

    @Test
    fun sameEncryptedByteArraysIfTokensAreSame() {
        val repository1 = DefaultEncryptionRepository("1234123412341234")
        val repository2 = DefaultEncryptionRepository("1234123412341234")
        val message = "A message to encrypt"
        val encrypted1 = repository1.encrypt(message)
        val encrypted2 = repository2.encrypt(message)
        Assert.assertEquals(encrypted1.contentToString(), encrypted2.contentToString())
    }

    @Test(expected = IllegalEncryptionTokenException::class)
    fun throwIfDecryptWithAnotherToken() {
        val repository1 = DefaultEncryptionRepository("1234123412341234")
        val repository2 = DefaultEncryptionRepository("4321432143214321")
        val message = "A message to encrypt"
        val encrypted = repository1.encrypt(message)
        repository2.decrypt(encrypted)
    }

    @Test
    fun decryptWithSameToken() {
        val repository1 = DefaultEncryptionRepository("1234123412341234")
        val repository2 = DefaultEncryptionRepository("1234123412341234")
        val message = "A message to encrypt"
        val encrypted = repository1.encrypt(message)
        val decrypted = repository2.decrypt(encrypted)
        Assert.assertEquals(message, decrypted)
    }

}