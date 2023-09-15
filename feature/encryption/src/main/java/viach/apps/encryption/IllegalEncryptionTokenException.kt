package viach.apps.encryption

internal class IllegalEncryptionTokenException(token: String) : IllegalStateException("token got: $token")