package viach.apps.shared.repository

import kotlin.reflect.KClass

interface JsonRepository {
    fun toJson(content: Any): String
    fun <T : Any> fromJson(json: String, contentClass: KClass<T>): T
}