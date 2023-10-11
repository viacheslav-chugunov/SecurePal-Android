package viach.apps.storage.model

class Lock(val secret: String, val type: Type) {
    enum class Type {
        EMPTY,
        PIN,
        PASSWORD
    }

    fun validate(input: String): Boolean = input == secret

    companion object {
        val DEFAULT: Lock = Lock("", Type.EMPTY)
    }
}