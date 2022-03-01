package io.github.devrawr.configs.shared.file

import java.io.File

interface FileWriter
{
    fun write(
        keys: Map<String, String>,
        file: File
    )
}