package io.github.devrawr.configs.shared.file.gson

import io.github.devrawr.configs.shared.file.FileHandler

object GsonFileHandler : FileHandler()
{
    override val fileReader = GsonFileReader
    override val fileWriter = GsonFileWriter
}