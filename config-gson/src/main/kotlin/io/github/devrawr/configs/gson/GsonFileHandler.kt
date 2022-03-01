package io.github.devrawr.configs.gson

import io.github.devrawr.configs.gson.wrappers.createGsonWrapper
import io.github.devrawr.configs.shared.ConfigCreator
import io.github.devrawr.configs.shared.file.FileHandler
import io.github.devrawr.configs.shared.naming.NamingScheme
import io.github.devrawr.configs.shared.naming.type.SnakeCaseNamingScheme

object GsonFileHandler : FileHandler()
{
    override val fileReader = GsonFileReader
    override val fileWriter = GsonFileWriter
    override val namingScheme = SnakeCaseNamingScheme

    init
    {
        ConfigCreator.createDefaultWrapper = {
            createGsonWrapper(it)
        }
    }
}