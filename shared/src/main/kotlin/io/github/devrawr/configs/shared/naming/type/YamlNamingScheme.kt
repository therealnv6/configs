package io.github.devrawr.configs.shared.naming.type

import io.github.devrawr.configs.shared.naming.NamingScheme

object YamlNamingScheme : NamingScheme
{
    private val SNAKE_CONVERT_REGEX = "(?<=[a-zA-Z])[A-Z]".toRegex()

    override fun renameFieldName(name: String): String
    {
        return SNAKE_CONVERT_REGEX.replace(name) {
            ".${it.value}"
        }
    }
}