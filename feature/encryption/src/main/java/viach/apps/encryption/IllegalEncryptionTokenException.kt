package viach.apps.encryption

class IllegalEncryptionTokenException(token: String) : IllegalStateException("token got: $token")