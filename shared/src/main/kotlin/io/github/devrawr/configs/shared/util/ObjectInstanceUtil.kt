package io.github.devrawr.configs.shared.util

import kotlin.reflect.KClass

object ObjectInstanceUtil
{
    fun <T : Any> KClass<*>.getOrCreateInstance(): T
    {
        return (this.objectInstance ?: this.java.newInstance()) as T
    }
}