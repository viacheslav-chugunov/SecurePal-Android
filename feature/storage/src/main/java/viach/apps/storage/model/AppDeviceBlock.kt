package viach.apps.storage.model

sealed class AppDeviceBlock(val type: String, val value: String) {

    object None : AppDeviceBlock("None", "None")

    class Pin(pin: String) : AppDeviceBlock("Pin", pin)

    class Password(password: String) : AppDeviceBlock("Password", password)

}