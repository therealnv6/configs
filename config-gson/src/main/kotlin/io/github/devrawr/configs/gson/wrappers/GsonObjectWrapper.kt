package io.github.devrawr.configs.gson.wrappers

import com.google.gson.Gson
import io.github.devrawr.configs.shared.wrapper.ObjectWrapper

class GsonObjectWrapper<T>(private val type: Class<T>) : ObjectWrapper<T>
{
    private val gson = Gson()

    override fun wrap(value: T): String
    {
        return gson.toJson(value)
    }

    override fun wrap(value: String): T
    {
        return gson.fromJson(value, type)
    }
}

fun <T> createGsonWrapper(type: Class<T>): GsonObjectWrapper<T>
{
    return GsonObjectWrapper(type)
}