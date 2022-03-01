package io.github.devrawr.configs.shared.naming

interface NamingScheme
{
    fun renameFieldName(name: String): String
}