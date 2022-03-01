package io.github.devrawr.configs.shared.wrapper

interface ObjectWrapper<T>
{
    fun wrap(value: T): String
    fun wrap(value: String): T

    fun wrapCasted(value: Any): String
    {
        return this.wrap(value as T)
    }
}