package io.github.devrawr.configs.gson

import io.github.devrawr.configs.gson.wrappers.createGsonWrapper
import io.github.devrawr.configs.shared.ConfigCreator
import io.github.devrawr.configs.shared.file.FileHandler

object GsonFileHandler : FileHandler()
{
    override val fileReader = GsonFileReader
    override val fileWriter = GsonFileWriter

    init
    {
        ConfigCreator.createDefaultWrapper = {
            createGsonWrapper(it)
        }
    }
}