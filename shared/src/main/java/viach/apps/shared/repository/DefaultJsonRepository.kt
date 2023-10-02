package viach.apps.shared.repository

import com.google.gson.Gson
import kotlin.reflect.KClass

internal class DefaultJsonRepository(
    private val gson: Gson
) : JsonRepository {

    override fun toJson(content: Any): String =
        gson.toJson(content)

    override fun <T : Any> fromJson(json: String, contentClass: KClass<T>): T =
        gson.fromJson(json, contentClass.java)
}