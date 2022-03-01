package io.github.devrawr.configs.shared.file

import java.io.File

interface FileReader
{
    fun read(file: File): Map<String, String>
}