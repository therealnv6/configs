package io.github.devrawr.configs.shared.file

import io.github.devrawr.configs.shared.naming.NamingScheme

abstract class FileHandler
{
    abstract val fileReader: FileReader
    abstract val fileWriter: FileWriter
    abstract val namingScheme: NamingScheme?
}