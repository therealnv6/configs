package io.github.devrawr.configs.shared.writer

import io.github.devrawr.configs.shared.file.FileWriter
import java.io.File

interface ConfigWriter
{
    fun write(
        origin: Any,
        writer: FileWriter,
        file: File,
    )
}