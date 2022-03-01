package io.github.devrawr.configs.shared.loader

import io.github.devrawr.configs.shared.file.FileReader
import java.io.File
import java.lang.reflect.Field

interface ConfigLoader
{
    fun load(
        origin: Any,
        reader: FileReader,
        file: File,
    ): Map<Field, Any>
}